package com.theitfox.picasso.presentation.utils;

import android.support.v7.util.DiffUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by btquanto on 28/09/2016.
 */

public class DiffTool<T> extends DiffUtil.Callback implements Observable.OnSubscribe<DiffUtil.DiffResult> {
    private List<T> oldList, newList;
    private ItemContentComparable itemContentComparable;
    private ItemComparable itemComparable;

    protected OnCompleteAction onCompleteAction;
    protected OnErrorAction onErrorAction;
    protected OnNextAction onNextAction;

    public DiffTool(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    public DiffTool<T> onCompareItems(ItemComparable<T> itemComparable) {
        this.itemComparable = itemComparable;
        return this;
    }

    public DiffTool<T> onCompareItemContents(ItemContentComparable<T> itemContentComparable) {
        this.itemContentComparable = itemContentComparable;
        return this;
    }

    public DiffTool<T> onComplete(OnCompleteAction action) {
        this.onCompleteAction = action;
        return this;
    }

    public DiffTool<T> onError(OnErrorAction action) {
        this.onErrorAction = action;
        return this;
    }

    public DiffTool<T> onNext(OnNextAction action) {
        this.onNextAction = action;
        return this;
    }

    @Override
    public void call(Subscriber<? super DiffUtil.DiffResult> subscriber) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(this);
        subscriber.onNext(diffResult);
    }

    public Subscription execute() {
        return Observable.create(this)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DiffToolSubscriber(onCompleteAction, onErrorAction, onNextAction));
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);

        if (itemComparable != null) {
            return itemComparable.areItemsTheSame(oldItem, newItem);
        }
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);

        if (itemContentComparable != null) {
            return itemContentComparable.areItemsEqual(oldItem, newItem);
        }
        return oldItem.equals(newItem);
    }

    public interface ItemContentComparable<T> {
        boolean areItemsEqual(T oldItem, T newItem);
    }

    public interface ItemComparable<T> {
        boolean areItemsTheSame(T oldItem, T newItem);
    }

    public interface OnCompleteAction {
        void onActionCompleted();
    }

    public interface OnErrorAction {
        void onErrorReceived(Throwable e);
    }

    public interface OnNextAction {
        void onNextAction(DiffUtil.DiffResult item);
    }

    private class DiffToolSubscriber extends Subscriber<DiffUtil.DiffResult> {
        private OnCompleteAction onCompleteAction;
        private OnErrorAction onErrorAction;
        private OnNextAction onNextAction;

        public DiffToolSubscriber(OnCompleteAction onCompleteAction, OnErrorAction onErrorAction, OnNextAction onNextAction) {
            super();
            this.onCompleteAction = onCompleteAction;
            this.onErrorAction = onErrorAction;
            this.onNextAction = onNextAction;
        }

        @Override
        public void onCompleted() {
            if (onCompleteAction != null) {
                onCompleteAction.onActionCompleted();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (onErrorAction != null) {
                onErrorAction.onErrorReceived(e);
            }
        }

        @Override
        public void onNext(DiffUtil.DiffResult item) {
            if (onNextAction != null) {
                onNextAction.onNextAction(item);
            }
        }
    }
}
