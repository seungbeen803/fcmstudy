package com.example.fcmstudy

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("mytag", message.notification?.title!!) // 노티용 제목 출력
        Log.d("mytag", message.notification?.body!!)  // 노티용 내용 출력
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // token : 디바이스 token 이라고 한다
        Log.d("mytag", token)
    }
}