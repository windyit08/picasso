package com.theitfox.picasso.presentation.common;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by btquanto on 26/09/2016.
 */

public abstract class Presenter<T extends BaseView> {
    protected T view;
    protected CompositeSubscription subscription;

    public Presenter() {
        this.subscription = new CompositeSubscription();
    }

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
        this.subscription.unsubscribe();
    }

    protected boolean isViewAttached() {
        return this.view != null;
    }
}
