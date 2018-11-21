package com.dscunikom.android.sma14bandung.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.activity.DetailAcaraActivity;
import com.dscunikom.android.sma14bandung.activity.DetailBeritaActivity;
import com.dscunikom.android.sma14bandung.activity.DetailPrestasiActivity;
import com.dscunikom.android.sma14bandung.activity.EkskulActivity;
import com.dscunikom.android.sma14bandung.activity.PreloadActivity;
import com.dscunikom.android.sma14bandung.rest.SessionManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    SessionManager sessionManager;
    Context context;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().size());

            onMessageRecivedAcara(remoteMessage);
            onMessageRecivedBerita(remoteMessage);
            onMessageRecivedPrestasi(remoteMessage);
            String activity = remoteMessage.getData().get("click_action");
            String body = remoteMessage.getData().get("body");
            Log.d(TAG, "ID ACARA : " + remoteMessage.getData().get("id_acara"));
            sendNotification(body,activity);
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


//            String activity = remoteMessage.getNotification().getClickAction();
//            sendNotification(remoteMessage.getNotification().getBody(),activity);
        }
//        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String body , String activity ) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        Intent intentNew = sendMessage(activity,intent);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentNew,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("SMAN 14 Bandung")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(body, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationBuilder.setChannelId(body);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        notificationManager.notify(1, notificationBuilder.build());
    }
    private void onMessageRecivedAcara(RemoteMessage remoteMessage){

            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.createIdAcara(remoteMessage.getData().get("id_acara"));
            Log.d("ID_ACARRA ","RESPONE "+String.valueOf(remoteMessage.getData().get("id_acara")));

    }
    private void onMessageRecivedBerita(RemoteMessage remoteMessage){


        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.createIdBerita(remoteMessage.getData().get("id_berita"));
        Log.d("ID_BERITA ","RESPONE "+String.valueOf(remoteMessage.getData().get("id_berita")));
    }
    private void onMessageRecivedPrestasi(RemoteMessage remoteMessage){

        if(remoteMessage.getData().get("id_prestasi") != null){
            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.createdIdPrestasi(remoteMessage.getData().get("id_prestasi"));
            Log.d("id_prestasi ","RESPONE "+String.valueOf(remoteMessage.getData().get("id_prestasi")));
        }

    }
    private Intent sendMessage(String activity, Intent intent){
        if(activity.equals("ACARAACTIVITY")){
            intent = new Intent(this,DetailAcaraActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if(activity.equals("BERITAACTIVITY")){
            intent = new Intent(this,DetailBeritaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else if(activity.equals("PRESTASIACTIVITY")){
            intent = new Intent(this,DetailPrestasiActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return intent;
    }
}
