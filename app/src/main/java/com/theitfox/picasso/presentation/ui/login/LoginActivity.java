package com.theitfox.picasso.presentation.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.theitfox.picasso.R;
import com.theitfox.picasso.presentation.ui.login.views.LoginFragment;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG_LOGIN_FRAGMENT = "login_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentManager fm = getSupportFragmentManager();
        Fragment loginFragment = fm.findFragmentByTag(TAG_LOGIN_FRAGMENT);
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fl_fragment_container, loginFragment, TAG_LOGIN_FRAGMENT)
                    .commit();
        }
    }
}
