package com.theitfox.picasso.presentation.ui.login.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.theitfox.picasso.R;
import com.theitfox.picasso.data.gson.LoginResponse;
import com.theitfox.picasso.data.gson.RegisterResponse;
import com.theitfox.picasso.presentation.ui.expandablelistview.ExpandableListViewActivity;
import com.theitfox.picasso.presentation.ui.login.presenters.LoginPresenterImpl;
import com.theitfox.picasso.presentation.ui.login.presenters.abstracts.LoginPresenter;
import com.theitfox.picasso.presentation.ui.login.views.abstracts.LoginView;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginFragment extends Fragment implements LoginView, View.OnClickListener {

    protected LoginPresenter presenter;
    protected EditText etUsername, etPassword, etEmail, etConfirm;

    public LoginFragment() {
        // Empty constructor for Fragment Manager
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.presenter == null) {
            this.presenter = new LoginPresenterImpl();
        }
        this.presenter.attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.presenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etConfirm = (EditText) view.findViewById(R.id.et_confirmation);

        // Event handlers
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        view.findViewById(R.id.btn_register).setOnClickListener(this);

        return view;
    }

    /**
     * LoginView methods
     */

    @Override
    public void onLoginSuccess(LoginResponse response) {
        Intent intent = new Intent(getContext(), ExpandableListViewActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError() {
        Toast.makeText(getContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccess(RegisterResponse response) {
        Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterError() {
        Toast.makeText(getContext(), "Registration Failed! It has not been implemented", Toast.LENGTH_SHORT).show();
    }

    /**
     * Event handlers
     */

    @Override
    public void onClick(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        String email = etEmail.getText().toString();

        switch (v.getId()) {
            case R.id.btn_login:
                presenter.login(username, password);
                break;
            case R.id.btn_register:
                // Validation should NOT be handled by presenter
                if (checkPasswordConfirmation(password, confirm)) {
                    presenter.register(username, password, email);
                } else {
                    Toast.makeText(getContext(), "Password confirmation mismatched", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * Private methods and functions
     */

    private boolean checkPasswordConfirmation(String password, String confirmation) {
        return password.equals(confirmation);
    }
}
