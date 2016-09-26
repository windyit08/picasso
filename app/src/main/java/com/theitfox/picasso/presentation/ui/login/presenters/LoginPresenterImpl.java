package com.theitfox.picasso.presentation.ui.login.presenters;

import com.theitfox.picasso.domain.usecases.LoginUseCase;
import com.theitfox.picasso.domain.usecases.RegisterUseCase;
import com.theitfox.picasso.presentation.ui.login.presenters.abstracts.LoginPresenter;
import com.theitfox.picasso.presentation.ui.login.views.abstracts.LoginView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginPresenterImpl implements LoginPresenter {
    protected CompositeSubscription subscription;
    protected LoginView view;

    public LoginPresenterImpl() {
        this.subscription = new CompositeSubscription();
    }

    @Override
    public void login(String username, String password) {
        LoginUseCase useCase = new LoginUseCase(username, password);
        this.subscription.add(useCase
                .onNext(response -> {
                    if (isViewAttached()) {
                        if (response.isSuccess()) {
                            view.onLoginSuccess(response);
                        } else {
                            view.onLoginError();
                        }
                    }
                }).execute());
    }

    @Override
    public void register(String username, String password, String email) {
        RegisterUseCase useCase = new RegisterUseCase(username, email, password);
        this.subscription.add(useCase
                .onNext(response -> {
                    if (isViewAttached()) {
                        if (response.isSuccess()) {
                            view.onRegisterSuccess(response);
                        } else {
                            view.onRegisterError();
                        }
                    }
                }).execute());
    }

    @Override
    public void attachView(LoginView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        this.subscription.unsubscribe();
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }
}
