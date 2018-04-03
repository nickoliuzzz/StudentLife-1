package com.a_team.studentlife.RegAndAuth;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.LinearLayout;

import com.a_team.studentlife.NavigationDrawerActivity;
import com.a_team.studentlife.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


        findViewById(R.id.logInButton).setOnClickListener(this);
        findViewById(R.id.signUpBottom).setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signUpBottom) {
            getWindow().setExitTransition(new Explode());
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            Intent intent = new Intent(this, NavigationDrawerActivity.class);
            startActivity(intent);
        }
    }
}
