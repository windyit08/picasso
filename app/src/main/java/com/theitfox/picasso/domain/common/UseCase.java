package com.theitfox.picasso.domain.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by btquanto on 26/09/2016.
 */

public abstract class UseCase<T> {

    protected final Scheduler executionThread;
    protected final Scheduler postExecutionThread;

    protected CompositeSubscription subscription;
    protected OnCompleteAction onCompleteAction;
    protected OnErrorAction onErrorAction;
    protected OnNextAction onNextAction;

    public UseCase() {
        this(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }

    public UseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
        this.subscription = new CompositeSubscription();
    }

    public UseCase<T> onComplete(OnCompleteAction action) {
        this.onCompleteAction = action;
        return this;
    }

    public UseCase<T> onError(OnErrorAction action) {
        this.onErrorAction = action;
        return this;
    }

    public UseCase<T> onNext(OnNextAction<T> action) {
        this.onNextAction = action;
        return this;
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public Subscription execute(Subscriber<T> useCaseSubscriber) {
        this.subscription.add(buildUseCaseObservable()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread)
                .subscribe(useCaseSubscriber));
        return this.subscription;
    }

    public Subscription execute() {
        return this.execute(new UseCaseSubscriber(onCompleteAction, onErrorAction, onNextAction));
    }

    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public interface OnCompleteAction {
        void onActionCompleted();
    }

    public interface OnErrorAction {
        void onErrorReceived(Throwable e);
    }

    public interface OnNextAction<T> {
        void onNextAction(T item);
    }

    private class UseCaseSubscriber extends Subscriber<T> {
        private OnCompleteAction onCompleteAction;
        private OnErrorAction onErrorAction;
        private OnNextAction onNextAction;

        public UseCaseSubscriber(OnCompleteAction onCompleteAction, OnErrorAction onErrorAction, OnNextAction onNextAction) {
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
        public void onNext(T item) {
            if (onNextAction != null) {
                onNextAction.onNextAction(item);
            }
        }
    }
}


