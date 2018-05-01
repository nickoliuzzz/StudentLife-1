package com.a_team.studentlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.SendReviewResponse;
import com.a_team.studentlife.UserInformation.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewActivity extends AppCompatActivity {
    private RadioGroup likeEventGroup;
    private RadioGroup connectionWithLearningGroup;
    private RadioGroup organizationLevelGroup;
    private RadioGroup doingAgainGroup;
    private Button sendReviewButton;
    private EditText psText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interview_activity);

        likeEventGroup = (RadioGroup) findViewById(R.id.like_event_button);
        connectionWithLearningGroup = (RadioGroup) findViewById(R.id.connection_with_learning_button);
        organizationLevelGroup = (RadioGroup) findViewById(R.id.organization_level);
        doingAgainGroup = (RadioGroup) findViewById(R.id.doing_again_button);
        sendReviewButton = (Button) findViewById(R.id.send_review_button);
        psText = (EditText) findViewById(R.id.PS_review_edit_text);
        setSendReviewButtonListener();
    }

    private void setSendReviewButtonListener() {
        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeEventGroup.getCheckedRadioButtonId() == -1 ||
                        connectionWithLearningGroup.getCheckedRadioButtonId() == -1 ||
                        organizationLevelGroup.getCheckedRadioButtonId() == -1 ||
                        doingAgainGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(
                            InterviewActivity.this,
                            "Заполните все необходимые поля",
                            Toast.LENGTH_SHORT).show();
                } else {
                    APIService mAPIService = ApiUtils.getAPIService();
                    RadioButton likeEventRadioButton =
                            (RadioButton) findViewById(likeEventGroup.getCheckedRadioButtonId());
                    RadioButton connectionWithLearningRadioButton =
                            (RadioButton) findViewById(connectionWithLearningGroup.getCheckedRadioButtonId());
                    RadioButton organizationLevelRadioButton =
                            (RadioButton) findViewById(organizationLevelGroup.getCheckedRadioButtonId());
                    RadioButton doingAgainRadioButton =
                            (RadioButton) findViewById(doingAgainGroup.getCheckedRadioButtonId());
                    mAPIService.sendReview(
                            User.getUserInstance().getId(),
                            likeEventRadioButton.getText().toString(),
                            connectionWithLearningRadioButton.getText().toString(),
                            organizationLevelRadioButton.getText().toString(),
                            doingAgainRadioButton.getText().toString(),
                            psText.getText().toString()
                    ).enqueue(new Callback<SendReviewResponse>() {
                        @Override
                        public void onResponse(Call<SendReviewResponse> call, Response<SendReviewResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getAnswerMessage().equals("ok")) {
                                    Toast.makeText(
                                            InterviewActivity.this,
                                            "Спасибо за оставленный отзыв",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SendReviewResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
