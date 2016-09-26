package com.theitfox.picasso.data.gson;

import java.io.Serializable;

/**
 * Created by btquanto on 26/09/2016.
 */

public class RegisterResponse implements Serializable {
    private static final String ERROR = "Register has not been implemented!";
    private boolean isSuccess;
    private String error;

    public RegisterResponse() {
        this.isSuccess = false;
        this.error = ERROR;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getError() {
        return error;
    }
}
