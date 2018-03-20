package com.studentlife.studentlife.RegistrationAndAuthentication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.studentlife.studentlife.R;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.APIService;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.Retrofit.ApiUtils;
import com.studentlife.studentlife.RegistrationAndAuthentication.Server.TestResponse.ServerResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nikit on 02/10/2018.
 */

public class registrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userNameTextField;
    private EditText emailTextField;
    private EditText firstNameTextField;
    private EditText secondNameTextField;
    private EditText passwordTextField;
    private CheckBox maleCheckBox, femaleCheckBox;
    private APIService mAPIService;
    private Boolean sex;
    private String error;
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    private TextView dateTextField;
    private String date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


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
        userNameTextField = (EditText) findViewById(R.id.userNameTextField);
        emailTextField = (EditText) findViewById(R.id.emailTextField);
        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        secondNameTextField = (EditText) findViewById(R.id.secondNameTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);
        maleCheckBox = (CheckBox) findViewById(R.id.maleCheckBox);
        femaleCheckBox = (CheckBox) findViewById(R.id.femaleCheckBox);
        dateTextField = (TextView) findViewById(R.id.dateTextField);
        dateTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(registrationActivity.this, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(61,15,69)));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                StringBuffer strBuffer = new StringBuffer();
                strBuffer.append(year + "-");
                strBuffer.append((month + 1) + "-");
                strBuffer.append(day);
                date = strBuffer.toString();
            }
        };
        findViewById(R.id.signUpBottom).setOnClickListener(this);
    }

    public void onCheckboxClicked(View view) {
        switch (view.getId()) {
            case R.id.maleCheckBox:
                femaleCheckBox.setChecked(false);
                sex = true;
                break;
            case R.id.femaleCheckBox:
                maleCheckBox.setChecked(false);
                sex = false;
                break;
            default:
                maleCheckBox.setChecked(false);
                femaleCheckBox.setChecked(false);
        }
    }

    @Override
    public void onClick(View view) {
        mAPIService.sendFirstName(firstNameTextField.getText().toString(), secondNameTextField.getText().toString(),
                userNameTextField.getText().toString(), passwordTextField.getText().toString(), emailTextField.getText().toString(), sex, date).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful() && response.body().getError() == "ok") {
                    Toast.makeText(registrationActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                }
                else {
                    error = response.body().getError();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(registrationActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

