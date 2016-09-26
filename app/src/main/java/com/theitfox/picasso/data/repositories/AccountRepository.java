package com.theitfox.picasso.data.repositories;

import com.theitfox.picasso.data.gson.LoginResponse;
import com.theitfox.picasso.data.gson.RegisterResponse;

import rx.Observable;

/**
 * Created by btquanto on 26/09/2016.
 */

public class AccountRepository {
    private static final String SECRET_USERNAME = "admin";
    private static final String SECRET_PASSWORD = "password";
    private static final String SECRET_KEY = "123456";

    public AccountRepository() {
    }

    public Observable<LoginResponse> login(String username, String password) {
        // Libraries like Retrofit helps creating these observables and gson response parsing
        return Observable.create(subscriber -> {
            // TODO: Should instead call an API and get the response from the server
            if (username.equals(SECRET_USERNAME) && password.equals(SECRET_PASSWORD)) {
                LoginResponse response = new LoginResponse(true, username, SECRET_KEY);
                subscriber.onNext(response);
            } else {
                LoginResponse response = new LoginResponse();
                subscriber.onNext(response);
            }
            subscriber.onCompleted();
        });
    }

    public Observable<RegisterResponse> register(String username, String email, String password) {
        return Observable.create(subscriber -> {
            // TODO: Should instead call an API and get the response from the server
            subscriber.onNext(new RegisterResponse());
            subscriber.onCompleted();
        });
    }
}
