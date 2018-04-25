package com.a_team.studentlife.RegAndAuth;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.a_team.studentlife.NavigationDrawerActivity;
import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.LoginResponse;
import com.a_team.studentlife.UserInformation.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    private EditText loginEditText;
    private EditText passwordEditText;
    private boolean exitFlag;

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        exitFlag = getIntent().getBooleanExtra("exitFlag", false);
        loginEditText = (EditText) findViewById(R.id.loginTextField);
        passwordEditText = (EditText) findViewById(R.id.passwordTextField);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        if (!exitFlag) {
            if (User.getUserInstance().readUserInformation(this))
                startActivity(new Intent(this, NavigationDrawerActivity.class));
        } else {
            User.getUserInstance().cleanUserInformation(this);
            loginEditText.setText(User.getUserInstance().getLogin());
            passwordEditText.setText(User.getUserInstance().getPassword());
        }

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
            APIService mAPIService = ApiUtils.getAPIService();
            mAPIService.login(loginEditText.getText().toString(), passwordEditText.getText().toString())
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful() && response.body().getError().equals("ok")) {
                                User.getUserInstance().setId(response.body().getId());
                                User.getUserInstance().setFirstName(response.body().getFirstName());
                                User.getUserInstance().setLastName(response.body().getLastName());
                                User.getUserInstance().setSex(response.body().getSex());
                                User.getUserInstance().setEmail(/*response.body().getEmail()*/"frfrfrfr@mail.ru");
                                User.getUserInstance().setLogin(/*response.body().getLogin()*/loginEditText.getText().toString());
                                User.getUserInstance().setPassword(passwordEditText.getText().toString());

                                if (!User.getUserInstance().saveUserInformation(LogInActivity.this))
                                    Toast.makeText(LogInActivity.this,
                                            "Ошибка записи данных пользователя в файл",
                                            Toast.LENGTH_SHORT).show();

                                Toast.makeText(LogInActivity.this, "Успешная авторизация",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogInActivity.this,
                                                            NavigationDrawerActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LogInActivity.this, "Ошибка",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LogInActivity.this, "Ошибка при авторизации",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
