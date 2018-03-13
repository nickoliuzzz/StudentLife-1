package com.studentlife.studentlife.RegistrationAndAuthentication;

/**
 * Created by nikit on 02/10/2018.
 */

public class registrationByEmailAndPassword extends authenticationAndRegistrationByEmailAndPassword {
    private authenticationAndRegistrationByEmailAndPassword registrationByEmailAndPassword;
    private String registrationnResult;

    public void registration(String email, String password) {
    }

    public String getRegistrationnResult() {
        return registrationnResult;
    }

    public void setRegistrationnResult(String authenticationResult) {
        this.registrationnResult = authenticationResult;
    }
}
