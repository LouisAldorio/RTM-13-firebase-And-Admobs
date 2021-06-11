package com.mindorks.framework.firebaseandadmobs

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val CHANNEL_1_ID = "channel1"
    val NOTIVY_1_ID = 1011

    override fun onMessageReceived(notif: RemoteMessage) {
        super.onMessageReceived(notif)

        if(notif.notification != null){
            showNotif(notif.notification!!.title, notif.notification!!.body)
        }
    }

    private fun showNotif(title: String?, body: String?) {

        var myNotivyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val myNotificationChannel = NotificationChannel(CHANNEL_1_ID,
            "Notification",
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            description="FCM Channel 1"
            enableLights(true)
            lightColor = Color.WHITE
        }
        myNotivyManager.createNotificationChannel(myNotificationChannel)

        var Notif = NotificationCompat.Builder(this, CHANNEL_1_ID).apply {
            setDefaults(Notification.DEFAULT_ALL)
            setWhen(System.currentTimeMillis())
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle(title)
            setContentText(body)
            setContentInfo("Info")
        }

        myNotivyManager.notify(NOTIVY_1_ID,Notif.build())
    }
}