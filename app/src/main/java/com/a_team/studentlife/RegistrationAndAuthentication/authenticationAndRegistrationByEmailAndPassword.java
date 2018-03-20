package com.studentlife.studentlife.RegistrationAndAuthentication;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by nikit on 02/10/2018.
 */

public class authenticationAndRegistrationByEmailAndPassword extends logInActivity implements View.OnClickListener {
    private static authenticationAndRegistrationByEmailAndPassword instance;
    Toast toast;
    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;

    public static synchronized authenticationAndRegistrationByEmailAndPassword getInstance() {
        if (instance == null)
            instance = new authenticationAndRegistrationByEmailAndPassword();
        return instance;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuthListener = mAuthListener;
    }
}
