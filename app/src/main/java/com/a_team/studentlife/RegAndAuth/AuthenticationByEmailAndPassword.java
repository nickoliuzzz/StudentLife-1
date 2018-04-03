package com.a_team.studentlife.RegAndAuth;

public class AuthenticationByEmailAndPassword extends AuthenticationAndRegistrationByEmailAndPassword {
    private AuthenticationAndRegistrationByEmailAndPassword authenticationByEmailAndPassword;
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
