package com.studentlife.studentlife.RegistrationAndAuthentication;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.studentlife.studentlife.R;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.APIService;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.Retrofit.ApiUtils;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.TestResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nikit on 02/10/2018.
 */

public class registrationActivity extends AppCompatActivity implements View.OnClickListener {
    private authenticationAndRegistrationByEmailAndPassword authenticationAndRegistrationByEmailAndPassword;
    private registrationByEmailAndPassword registrationByEmailAndPassword;
    private EditText loginTextField;
    private EditText passwordTextField;
    private APIService mAPIService;
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_singup);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        mAPIService = ApiUtils.getAPIService();
        loginTextField = linearLayout.findViewById(R.id.loginTextField);

        findViewById(R.id.signUpBottom).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mAPIService.sendFirstName("Lol","Lol","Lol","1234567890","Lol@gmail.com",true, "1997-09-28").enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(registrationActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registrationActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(registrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

