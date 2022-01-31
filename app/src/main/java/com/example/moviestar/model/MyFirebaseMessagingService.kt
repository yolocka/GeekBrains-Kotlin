package com.example.moviestar.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.moviestar.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val TAG = "FirebaseMessaging"
const val NOTIFICATION_ID = 12
const val CHANNEL_ID = "Default"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d(TAG, "onMessageReceived $p0")
        super.onMessageReceived(p0)


        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Мое сообщение")
            .setSmallIcon(R.drawable.adults_only_icon)
            .setStyle(NotificationCompat.BigTextStyle())

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = ""
               }
            )
            notificationBuilder.setChannelId(CHANNEL_ID)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, "onNewToken $p0")
    }


}