package com.studentlife.studentlife.RegistrationAndAuthentication;

/**
 * Created by nikit on 02/10/2018.
 */

public class authenticationByEmailAndPassword extends authenticationAndRegistrationByEmailAndPassword {
    private authenticationAndRegistrationByEmailAndPassword authenticationByEmailAndPassword;
    private String authenticationResult;

    public void signIn(String email, String password) {
    }

    public String getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(String authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
}
