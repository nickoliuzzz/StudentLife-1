package com.a_team.studentlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class NotificationsActivity extends AppCompatActivity {

    private Switch allNotificationsSwitch;
    private Switch subAndNewsNotificationsSwitch;
    private Switch questNotificationsSwitch;
    private Button saveNotificationsSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);

        subAndNewsNotificationsSwitch = (Switch) findViewById(R.id.sub_and_news_notifications_switch);
        questNotificationsSwitch = (Switch) findViewById(R.id.questions_notifications_switch);
        allNotificationsSwitch = (Switch) findViewById(R.id.all_notifications_switch);
        allNotificationsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allNotificationsSwitch.isChecked()) {
                    subAndNewsNotificationsSwitch.setChecked(false);
                    subAndNewsNotificationsSwitch.setEnabled(false);
                    questNotificationsSwitch.setChecked(false);
                    questNotificationsSwitch.setEnabled(false);
                } else {
                    subAndNewsNotificationsSwitch.setChecked(true);
                    subAndNewsNotificationsSwitch.setEnabled(true);
                    questNotificationsSwitch.setChecked(true);
                    questNotificationsSwitch.setEnabled(true);
                }
            }
        });
        saveNotificationsSettingsButton = (Button) findViewById(R.id.save_notifications_settings);
        setTrueSwitchCheckedState();
        setSaveNotificationsSettingsButtonListener();
    }

    private void setSaveNotificationsSettingsButtonListener() {
        saveNotificationsSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allNotificationsSwitch.isChecked()) {

                    // Остановить все службы

                    finish();
                }
                if (questNotificationsSwitch.isChecked()) {
                    // Включить соответствующую службу
                } else {
                    // Отключить соответствующую службу
                }
                if (subAndNewsNotificationsSwitch.isChecked()) {
                    // Включить соответствующую службу
                } else {
                    // Отключить соответствующую службу
                }
                finish();
            }
        });
    }

    private void setTrueSwitchCheckedState() {
        // Проверить работу служб и установить switches в корректное состояние
    }
}
