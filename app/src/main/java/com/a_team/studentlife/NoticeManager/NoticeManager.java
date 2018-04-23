package com.a_team.studentlife.NoticeManager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

import com.a_team.studentlife.R;

@SuppressLint("NewApi")
public class NoticeManager {
    public static void showNotification(Context context,
                                        String ticker,
                                        String title,
                                        String text,
                                        int notificationId) {
        Notification.Builder builder = new Notification.Builder(context);
        builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setTicker(ticker)
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager nm = (NotificationManager) context.getApplicationContext()
                                                              .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(notificationId, notification);
        }
    }
}
