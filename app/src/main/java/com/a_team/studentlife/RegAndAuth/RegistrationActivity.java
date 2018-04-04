package com.a_team.studentlife.RegAndAuth;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.NavigationDrawerActivity;
import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.RegistrationResponse;
import com.a_team.studentlife.UserInformation.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameTextField;
    private EditText emailTextField;
    private EditText firstNameTextField;
    private EditText secondNameTextField;
    private EditText passwordTextField;
    private CheckBox maleCheckBox, femaleCheckBox;
    private APIService mAPIService;
    private int sex;
    private String error;
    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    private TextView dateTextField;
    private String date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

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
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setNavigationBarColor(Color.WHITE);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(61,15,69)));
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
                sex = 1;
                break;
            case R.id.femaleCheckBox:
                maleCheckBox.setChecked(false);
                sex = 0;
                break;
            default:
                maleCheckBox.setChecked(false);
                femaleCheckBox.setChecked(false);
        }
    }

    @Override
    public void onClick(final View view) {
        mAPIService.sendFirstName(firstNameTextField.getText().toString(), secondNameTextField.getText().toString(),
                userNameTextField.getText().toString(), passwordTextField.getText()
                .toString(), emailTextField.getText().toString(), sex, date)
                .enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful() && response.body().getError().equals("ok")) {
                    User.getUserInstance().setId(response.body().getId());
                    User.getUserInstance().setFirstName(firstNameTextField.getText().toString());
                    User.getUserInstance().setLastName(secondNameTextField.getText().toString());
                    Toast.makeText(RegistrationActivity.this, "Успешная регистрация",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), NavigationDrawerActivity.class);
                    startActivity(intent);
                } else {
                    error = response.body().getError();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
