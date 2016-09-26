package com.theitfox.picasso.domain.usecases;

import com.theitfox.picasso.data.gson.LoginResponse;
import com.theitfox.picasso.data.repositories.AccountRepository;
import com.theitfox.picasso.domain.common.UseCase;

import rx.Observable;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginUseCase extends UseCase<LoginResponse> {
    private AccountRepository repository;
    private String username;
    private String password;

    public LoginUseCase(String username, String password) {
        this.username = username;
        this.password = password;
        this.repository = new AccountRepository();
    }

    @Override
    protected Observable<LoginResponse> buildUseCaseObservable() {
        return repository.login(username, password);
    }
}
