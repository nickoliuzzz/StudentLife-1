package com.a_team.studentlife.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.a_team.studentlife.NoticeManager.NoticeManager;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.CheckSubAndNewsResponse;
import com.a_team.studentlife.UserInformation.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAndSubscriptionService extends Service {

    final class CheckServerResponseThread implements Runnable {

        int serviceId;
        int requestPeriod;

        CheckServerResponseThread(int serviceId, int requestPeriod) {
            this.serviceId = serviceId;
            this.requestPeriod = requestPeriod;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (true) {
                    // Запрос на сервер

                    APIService mAPIService = ApiUtils.getAPIService();
                    mAPIService.checkSubAndNews(User.getUserInstance().getId()).enqueue(new Callback<CheckSubAndNewsResponse>() {
                        @Override
                        public void onResponse(Call<CheckSubAndNewsResponse> call, Response<CheckSubAndNewsResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getAnswerMessage().equals("ok")) {
                                    NoticeManager.showNotification(
                                            NewsAndSubscriptionService.this,
                                            "Уведомление от StudentLife",
                                            response.body().getNotificationTitle(),
                                            response.body().getNotificationText(),
                                            1,
                                            1);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckSubAndNewsResponse> call, Throwable t) {

                        }
                    });

                    try {
                        wait(this.requestPeriod);
                    } catch (InterruptedException e) {
                        Toast.makeText(NewsAndSubscriptionService.this,
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
