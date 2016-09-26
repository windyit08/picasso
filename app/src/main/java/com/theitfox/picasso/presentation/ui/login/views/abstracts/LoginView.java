package com.theitfox.picasso.presentation.ui.login.views.abstracts;

import com.theitfox.picasso.data.gson.LoginResponse;
import com.theitfox.picasso.data.gson.RegisterResponse;
import com.theitfox.picasso.presentation.common.BaseView;

/**
 * Created by btquanto on 26/09/2016.
 */

public interface LoginView extends BaseView {

    void onLoginSuccess(LoginResponse response);

    void onLoginError();

    void onRegisterSuccess(RegisterResponse response);

    void onRegisterError();
}
