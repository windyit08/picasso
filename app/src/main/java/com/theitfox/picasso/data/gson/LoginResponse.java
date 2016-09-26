package com.theitfox.picasso.data.gson;

import java.io.Serializable;

/**
 * Created by btquanto on 26/09/2016.
 */

public class LoginResponse implements Serializable {
    private boolean isSuccess;
    private String username;
    private String secretKey;

    public LoginResponse() {
        this.isSuccess = false;
        this.username = null;
        this.secretKey = null;
    }

    public LoginResponse(boolean isSuccess, String username, String secretKey) {
        this.isSuccess = isSuccess;
        this.username = username;
        this.secretKey = secretKey;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getUsername() {
        return username;
    }

    public String getSecretKey() {
        return secretKey;
    }

}
