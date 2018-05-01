package com.a_team.studentlife.NoticeManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.a_team.studentlife.InterviewActivity;
import com.a_team.studentlife.R;

public class NoticeManager {
    public static void showNotification(Context context,
                                        String ticker,
                                        String title,
                                        String text,
                                        int notificationId,
                                        int notificationFlag) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notify_001")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setTicker(ticker)
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

        if (notificationFlag == 2) {
            Intent intent = new Intent(context.getApplicationContext(), InterviewActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context.getApplicationContext(),
                    0,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        NotificationManager nm = (NotificationManager) context.getApplicationContext()
                                                              .getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notify_001",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }

        if (nm != null) {
            nm.notify(notificationId, builder.build());
        }
    }
}
