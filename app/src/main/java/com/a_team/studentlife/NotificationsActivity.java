package com.a_team.studentlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.a_team.studentlife.Services.NewsAndSubscriptionService;
import com.a_team.studentlife.Services.QuizService;
import com.a_team.studentlife.Services.ServiceManager;

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
        subAndNewsNotificationsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subAndNewsNotificationsSwitch.isChecked() && !questNotificationsSwitch.isChecked()) {
                    allNotificationsSwitch.setChecked(false);
                    setStateSubAndNewsAndQuesNotificationSwitches(false);
                }
            }
        });
        questNotificationsSwitch = (Switch) findViewById(R.id.questions_notifications_switch);
        questNotificationsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subAndNewsNotificationsSwitch.isChecked() && !questNotificationsSwitch.isChecked()) {
                    allNotificationsSwitch.setChecked(false);
                    setStateSubAndNewsAndQuesNotificationSwitches(false);
                }
            }
        });
        allNotificationsSwitch = (Switch) findViewById(R.id.all_notifications_switch);
        allNotificationsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allNotificationsSwitch.isChecked()) {
                    setStateSubAndNewsAndQuesNotificationSwitches(false);
                } else {
                    setStateSubAndNewsAndQuesNotificationSwitches(true);
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
                    stopService(new Intent(
                            NotificationsActivity.this,
                            NewsAndSubscriptionService.class));
                    stopService(new Intent(
                            NotificationsActivity.this,
                            QuizService.class
                    ));
                    finish();
                }
                if (questNotificationsSwitch.isChecked()) {
                    startService(new Intent(
                            NotificationsActivity.this,
                            QuizService.class
                    ));
                } else {
                    stopService(new Intent(
                            NotificationsActivity.this,
                            QuizService.class
                    ));
                }
                if (subAndNewsNotificationsSwitch.isChecked()) {
                    startService(new Intent(
                            NotificationsActivity.this,
                            NewsAndSubscriptionService.class
                    ));
                } else {
                    stopService(new Intent(
                            NotificationsActivity.this,
                            NewsAndSubscriptionService.class
                    ));
                }
                finish();
            }
        });
    }

    private void setTrueSwitchCheckedState() {
        // Проверить работу служб и установить switches в корректное состояние
        if (ServiceManager.isServiceRunning(NewsAndSubscriptionService.class, this)) {
            subAndNewsNotificationsSwitch.setChecked(true);
            subAndNewsNotificationsSwitch.setEnabled(true);
        } else
            subAndNewsNotificationsSwitch.setChecked(false);
        if (ServiceManager.isServiceRunning(QuizService.class, this)) {
            questNotificationsSwitch.setChecked(true);
            questNotificationsSwitch.setEnabled(true);
        } else
            questNotificationsSwitch.setChecked(false);
        if (!subAndNewsNotificationsSwitch.isChecked() && !questNotificationsSwitch.isChecked()) {
            allNotificationsSwitch.setChecked(false);
            subAndNewsNotificationsSwitch.setEnabled(false);
            questNotificationsSwitch.setEnabled(false);
        } else {
            allNotificationsSwitch.setChecked(true);
            subAndNewsNotificationsSwitch.setEnabled(true);
            questNotificationsSwitch.setEnabled(true);
        }
    }

    private void setStateSubAndNewsAndQuesNotificationSwitches(boolean flag) {
        subAndNewsNotificationsSwitch.setChecked(flag);
        subAndNewsNotificationsSwitch.setEnabled(flag);
        questNotificationsSwitch.setChecked(flag);
        questNotificationsSwitch.setEnabled(flag);
    }
}
