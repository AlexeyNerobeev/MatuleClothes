package com.example.matuleclothes

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.example.matuleclothes.domain.usecase.SendNotificationUseCase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val sendNotificationUseCase = SendNotificationUseCase(this.applicationContext)
        sendNotificationUseCase.invoke("qwe", "qwe")

//        val notification = NotificationCompat.Builder(this.applicationContext, "id")
//            .setSmallIcon(R.drawable.settings_icon)
//            .setContentTitle("qwertyuiop")
//            .setContentText("pl,lopol,lopl")
//            .build()
//
//        val manager = this.applicationContext.getSystemService(NotificationManager::class.java)
//        val channel = NotificationChannel("id", "pop", NotificationManager.IMPORTANCE_MAX)
//        manager.createNotificationChannel(channel)
//
//        manager.notify(0, notification)

    }
}