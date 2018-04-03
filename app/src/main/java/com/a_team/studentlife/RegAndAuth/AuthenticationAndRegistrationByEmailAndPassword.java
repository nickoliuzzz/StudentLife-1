package com.a_team.studentlife.RegAndAuth;

import android.view.View;
import android.widget.Toast;

public class AuthenticationAndRegistrationByEmailAndPassword extends LogInActivity implements View.OnClickListener {
    private static AuthenticationAndRegistrationByEmailAndPassword instance;
    Toast toast;

    public static synchronized AuthenticationAndRegistrationByEmailAndPassword getInstance() {
        if (instance == null)
            instance = new AuthenticationAndRegistrationByEmailAndPassword();
        return instance;
    }
}
