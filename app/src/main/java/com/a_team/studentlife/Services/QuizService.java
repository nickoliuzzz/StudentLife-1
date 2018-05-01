package com.a_team.studentlife.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.a_team.studentlife.NoticeManager.NoticeManager;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.CheckQuizResponse;
import com.a_team.studentlife.UserInformation.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizService extends Service {

    final class CheckServerResponseThread implements Runnable {

        int serviceId;
        int requestPeriod;

        public CheckServerResponseThread(int serviceId, int requestPeriod) {
            this.serviceId = serviceId;
            this.requestPeriod = requestPeriod;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (true) {
                    // Запрос на сервер

                    APIService mAPIService = ApiUtils.getAPIService();
                    mAPIService.checkQuiz(User.getUserInstance().getId()).enqueue(new Callback<CheckQuizResponse>() {
                        @Override
                        public void onResponse(Call<CheckQuizResponse> call, Response<CheckQuizResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getAnswerMessage().equals("ok")) {
                                    NoticeManager.showNotification(
                                            QuizService.this,
                                            "Опрос от StudentLife",
                                            response.body().getNotificationTitle(),
                                            response.body().getNotificationText(),
                                            2,
                                            2);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckQuizResponse> call, Throwable t) {

                        }
                    });

                    try {
                        wait(this.requestPeriod);
                    } catch (InterruptedException e) {
                        Toast.makeText(QuizService.this,
                                "Произошла ошибка при выполнения ожидания",
                                Toast.LENGTH_SHORT).show();
                        stopSelf(this.serviceId);
                    }
                }
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(new CheckServerResponseThread(
                startId,
                intent.getIntExtra("requestPeriod", 5000))
        );
        thread.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
