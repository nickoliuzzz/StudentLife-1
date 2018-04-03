package com.a_team.studentlife.RegAndAuth;

public class RegistrationByEmailAndPassword extends AuthenticationAndRegistrationByEmailAndPassword {
    private AuthenticationAndRegistrationByEmailAndPassword registrationByEmailAndPassword;
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
