package com.theitfox.picasso.presentation.common;

/**
 * Created by btquanto on 26/09/2016.
 */

public interface Presenter<T extends BaseView> {

    void attachView(T view);

    void detachView();

    boolean isViewAttached();
}
