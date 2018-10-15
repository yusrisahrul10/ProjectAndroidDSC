package com.dscunikom.android.sma14bandung.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dscunikom.android.sma14bandung.R;
import com.dscunikom.android.sma14bandung.activity.DetailAcaraActivity;
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
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            onMessageRecivedAcara(remoteMessage);

            String activity = remoteMessage.getNotification().getClickAction();
            sendNotification(remoteMessage.getNotification().getBody(),activity);
        }
//        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String body , String activity ) {
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

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
    private void onMessageRecivedAcara(RemoteMessage remoteMessage){
        try {
            JSONObject data = new JSONObject(remoteMessage.getData());
            String id = data.getString("id_acara");
            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.createIdAcara(id);
            Log.d("ID_ACARRA ","RESPONE "+String.valueOf(id));
        } catch (JSONException e) {
            e.printStackTrace();


        }
    }
    private Intent sendMessage(String activity, Intent intent){
        if(activity.equals("DETAILACTIVITY")){
            intent = new Intent(this,DetailAcaraActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return intent;
    }
}
