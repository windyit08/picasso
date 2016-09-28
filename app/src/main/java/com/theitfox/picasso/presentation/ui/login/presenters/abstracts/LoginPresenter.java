package com.theitfox.picasso.presentation.ui.login.presenters.abstracts;

import com.theitfox.picasso.presentation.common.Presenter;
import com.theitfox.picasso.presentation.ui.login.views.abstracts.LoginView;

/**
 * Created by btquanto on 26/09/2016.
 */

public abstract class LoginPresenter extends Presenter<LoginView> {
    public abstract void login(String username, String password);

    public abstract void register(String username, String password, String email);
}
