package com.a_team.studentlife.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
