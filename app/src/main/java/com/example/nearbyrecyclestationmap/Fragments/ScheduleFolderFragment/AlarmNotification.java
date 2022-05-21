package com.example.nearbyrecyclestationmap.Fragments.ScheduleFolderFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.nearbyrecyclestationmap.Activity.MainActivity;
import com.example.nearbyrecyclestationmap.R;

public class AlarmNotification extends BroadcastReceiver {

    public final String CHANNEL_ID="ECO_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId=intent.getIntExtra("notificationId",0);
        String message=intent.getStringExtra("message");

        Intent mainIntent=new Intent(context, MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence channelName="Eco Notification";

            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,channelName,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setContentTitle("Eco Reminder")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notificationManager.notify(notificationId,builder.build());
    }
}
