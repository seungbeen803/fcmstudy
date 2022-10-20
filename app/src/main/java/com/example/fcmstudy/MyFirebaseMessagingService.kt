package com.example.fcmstudy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Log.d("mytag", message.notification?.title!!) // 노티용 제목 출력
        // Log.d("mytag", message.notification?.body!!)  // 노티용 내용 출력
        var builder = NotificationCompat.Builder(this)
            .setSmallIcon(android.R.drawable.ic_notification_clear_all)
            .setContentTitle(message.notification?.title!!)
            .setContentText(message.notification?.body!!)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        Log.d("mytag", message.data.toString())

        val notificationManager = NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // channel : 설정한 것만 알림을 받는 기능
            val channelId = "fcm_message"
            val channel =
                NotificationChannel(
                channelId,
                "Channel Title",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelId)
        }

        // https://stackoverflow.com/questions/39607856/what-is-notification-id-in-android
        // id를 똑같이 주면 덮어써지고, id를 다르게 주면 새로 만든다 (알림이 쌓이는 기능)
        notificationManager.notify(1, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // token : 디바이스 token 이라고 한다
        Log.d("mytag", token)
    }
}