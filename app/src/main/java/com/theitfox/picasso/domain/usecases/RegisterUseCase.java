package com.theitfox.picasso.domain.usecases;

import com.theitfox.picasso.data.gson.RegisterResponse;
import com.theitfox.picasso.data.repositories.AccountRepository;
import com.theitfox.picasso.domain.common.UseCase;

import rx.Observable;

/**
 * Created by btquanto on 26/09/2016.
 */

public class RegisterUseCase extends UseCase<RegisterResponse> {
    private AccountRepository repository;
    private String username;
    private String email;
    private String password;

    public RegisterUseCase(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repository = new AccountRepository();
    }

    @Override
    protected Observable<RegisterResponse> buildUseCaseObservable() {
        return repository.register(username, email, password);
    }
}
