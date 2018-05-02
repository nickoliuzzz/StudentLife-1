package com.a_team.studentlife.UserInformation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ChangeUserInformationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUserInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private String firstName, lastName;
    private Integer id;
    private EditText emailTextField;
    private EditText firstNameTextField;
    private EditText secondNameTextField;
    private EditText passwordTextField;
    private EditText rePasswordTextField;
    private EditText reRePasswordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_user_information_activity);

        emailTextField = (EditText) findViewById(R.id.emailTextField);
        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        secondNameTextField = (EditText) findViewById(R.id.secondNameTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);
        rePasswordTextField = (EditText) findViewById(R.id.rePasswordTextField);
        reRePasswordTextField = (EditText) findViewById(R.id.reRePasswordTextField);

        findViewById(R.id.changeInformationBottom).setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        String password;
        if (rePasswordTextField.getText() == reRePasswordTextField.getText()) {
            password = rePasswordTextField.getText().toString();
        } else {
            password = passwordTextField.getText().toString();
        }
        if (view.getId() == R.id.changeInformationBottom) {
            APIService mAPIService = ApiUtils.getAPIService();
            id = User.getUserInstance().getId();
            mAPIService.changeUserInformation(id.toString(), emailTextField.getText().toString(), firstNameTextField.getText().toString(),
                    secondNameTextField.getText().toString(), password)
                    .enqueue(new Callback<ChangeUserInformationResponse>() {
                        @Override
                        public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                            String error = response.body().getError();
                            if (response.isSuccessful() && response.body().getError().equals("ok")) {
                                Toast.makeText(ChangeUserInformationActivity.this, "Успешная замена данных",
                                        Toast.LENGTH_SHORT).show();
                                if (firstNameTextField.getText().toString() == null) {
                                    firstName = User.getUserInstance().getFirstName();
                                } else {
                                    firstName = firstNameTextField.getText().toString();
                                }
                                if (secondNameTextField.getText().toString() == null) {
                                    lastName = User.getUserInstance().getLastName();
                                } else {
                                    lastName = secondNameTextField.getText().toString();
                                }
                                User.getUserInstance().setFirstName(firstName);
                                User.getUserInstance().setLastName(lastName);
                            } else {
                                Toast.makeText(ChangeUserInformationActivity.this, "Ошибка замены данных" + " " + error,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
                            Toast.makeText(ChangeUserInformationActivity.this, "Ошибка при авторизации",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
