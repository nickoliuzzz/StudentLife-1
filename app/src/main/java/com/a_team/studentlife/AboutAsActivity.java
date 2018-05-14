package com.a_team.studentlife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutAsActivity extends AppCompatActivity {

    @BindView(R.id.aleksey_button)
    ImageButton aleksey_button;

    @BindView(R.id.nikolay_button)
    ImageButton nikolay_button;

    @BindView(R.id.illya_button)
    ImageButton illya_button;

    @BindView(R.id.darya_button)
    ImageButton darya_button;

    @BindView(R.id.nikita_button)
    ImageButton nikita_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_as_activity);
        ButterKnife.bind(this);
        setButtonsClickListeners();
    }

    private void setButtonsClickListeners() {
        aleksey_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/kuzmenko_aleshka");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
        nikolay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/id365203646");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
        darya_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/dashkachiruk");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
        illya_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/directblow1");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
        nikita_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/nikitabytsko");
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
    }
}
