package com.studentlife.studentlife.RegistrationAndAuthentication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import com.studentlife.studentlife.R;

public class logInActivity extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        findViewById(R.id.logInButton).setOnClickListener(this);
        findViewById(R.id.signUpBottom).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getWindow().setExitTransition(new Explode());
       Intent intent = new Intent(this, registrationActivity.class);
       startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
