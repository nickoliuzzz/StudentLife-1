package com.a_team.studentlife;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.CreateLeagueResponse;
import com.a_team.studentlife.UserInformation.User;
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
    private ImageButton getCameraImage;
    private ImageButton getMobileImage;
    private ImageButton cancelImage;
    private ScrollView scrollView;
    private AnimationDrawable animationDrawable;
    private ImageView imageForLeague;
    private boolean imageByMobileAndCamera = false;

    static final int REQUEST_IMAGE_CAPTURE_CAMERA = 1;
    static final int REQUEST_IMAGE_CAPTURE_GALLERY = 2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_league_activity);
        setTitle("Создание лиги");

        scrollView = (ScrollView) findViewById(R.id.linear_layout_create_league);
        animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        Intent intent = getIntent();
        leagueListElement = new LeagueListElement(
                intent.getIntExtra("leagueIndex", 0),
                intent.getStringExtra("leagueName"),
                intent.getIntExtra("subKey", 0)
        );

        imageForLeague = (ImageView) findViewById(R.id.imageForLeague);
        getCameraImage = (ImageButton) findViewById(R.id.getPhotoByCamera);
        getMobileImage = (ImageButton) findViewById(R.id.getPhotoByMobile);
        cancelImage = (ImageButton) findViewById(R.id.cancelImage);
        setImageButtonsListeners();

        createLeagueHeaderTextView = (TextView) findViewById(R.id.createLeagueHeaderTextView);
        createLeagueNameTextEdit = (EditText) findViewById(R.id.createLeagueNameTextEdit);
        createLeagueDescriptionTextEdit = (EditText) findViewById(R.id.createLeagueDescriptionTextEdit);
        createChildLeagueButton = (Button) findViewById(R.id.createChildLeagueButton);
        setCreateChildLeagueButtonListener(createChildLeagueButton, this);

        createLeagueHeaderTextView.setText(createLeagueHeaderTextView.getText() + " " + leagueListElement.getLeagueName());

    }

    private void setImageButtonsListeners() {
        getCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_CAMERA);
                    imageByMobileAndCamera = true;
                }
            }
        });
        getMobileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_GALLERY);
            }
        });
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageByMobileAndCamera = false;
                imageForLeague.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageForLeague.setVisibility(View.VISIBLE);
            imageForLeague.setImageBitmap(imageBitmap);
        } else if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE_GALLERY) {
            Uri imageURI = data.getData();
            //Toast.makeText(this, data.getData().toString(), Toast.LENGTH_LONG).show();
            imageForLeague.setVisibility(View.VISIBLE);
            imageForLeague.setImageURI(imageURI);
        }

//        File file = new File(filePath);
//        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
    }

    private void setCreateChildLeagueButtonListener(final Button createChildLeagueButton, final Context context) {
        createChildLeagueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService mAPIService = ApiUtils.getAPIService();
                mAPIService.createChildLeague(User.getUserInstance().getId(),
                                                leagueListElement.getLeagueIndex(),
                                                createLeagueNameTextEdit.getText().toString(),
                                                createLeagueDescriptionTextEdit.getText().toString(), "dedded")
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
