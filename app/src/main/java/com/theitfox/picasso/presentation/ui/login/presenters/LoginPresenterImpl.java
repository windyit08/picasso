package com.theitfox.picasso.presentation.ui.login.presenters;

import com.theitfox.picasso.domain.usecases.LoginUseCase;
import com.theitfox.picasso.domain.usecases.RegisterUseCase;
import com.theitfox.picasso.presentation.ui.login.presenters.abstracts.LoginPresenter;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginPresenterImpl extends LoginPresenter {

    public LoginPresenterImpl() {
        super();
    }

    @Override
    public void login(String username, String password) {
        LoginUseCase useCase = new LoginUseCase(username, password);
        subscription.add(useCase
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
        subscription.add(useCase
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
}
