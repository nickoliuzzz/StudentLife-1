package com.a_team.studentlife;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.CreateLeagueResponse;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateLeagueActivity extends AppCompatActivity {

    private TextView createLeagueHeaderTextView;
    private EditText createLeagueNameTextEdit;
    private EditText createLeagueDescriptionTextEdit;
    private LeagueListElement leagueListElement;
    private Button createChildLeagueButton;
    private LinearLayout linearLayout;
    private AnimationDrawable animationDrawable;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_league_activity);
        setTitle("Создание лиги");

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_create_league);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        Intent intent = getIntent();
        leagueListElement = new LeagueListElement(
                intent.getIntExtra("leagueIndex", 0),
                intent.getStringExtra("leagueName")
        );

        createLeagueHeaderTextView = (TextView) findViewById(R.id.createLeagueHeaderTextView);
        createLeagueNameTextEdit = (EditText) findViewById(R.id.createLeagueNameTextEdit);
        createLeagueDescriptionTextEdit = (EditText) findViewById(R.id.createLeagueDescriptionTextEdit);
        createChildLeagueButton = (Button) findViewById(R.id.createChildLeagueButton);
        setCreateChildLeagueButtonListener(createChildLeagueButton, this);

        createLeagueHeaderTextView.setText(createLeagueHeaderTextView.getText() + " " + leagueListElement.getLeagueName());

    }

    private void setCreateChildLeagueButtonListener(final Button createChildLeagueButton, final Context context) {
        createChildLeagueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService mAPIService = ApiUtils.getAPIService();
                mAPIService.createChildLeague(leagueListElement.getLeagueIndex(),
                                                createLeagueNameTextEdit.getText().toString(),
                                                createLeagueDescriptionTextEdit.getText().toString())
                        .enqueue(new Callback<CreateLeagueResponse>() {
                    @Override
                    public void onResponse(Call<CreateLeagueResponse> call, Response<CreateLeagueResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getResponse().equals("create")) {
                                Toast.makeText(context, response.body().getResponse(),
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(context, response.body().getResponse(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Ошибка выполнения запроса", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateLeagueResponse> call, Throwable t) {
                        ProgressService.showDialogMessage(context, "Ошибка соединения",
                                "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                                2148, true);
                    }
                });
            }
        });
    }
}
