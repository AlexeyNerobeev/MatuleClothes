package com.example.matuleclothes.domain.usecase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.matuleclothes.R
import dagger.hilt.android.qualifiers.ApplicationContext

class SendNotificationUseCase(
    @param:ApplicationContext private val context: Context
) {
    operator fun invoke(title: String, text: String){
        val notification = NotificationCompat.Builder(context, "id")
            .setSmallIcon(R.drawable.settings_icon)
            .setContentTitle(title)
            .setContentText(text)
            .build()

        val manager = context.getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel("id", "matule", NotificationManager.IMPORTANCE_MAX)
        manager.createNotificationChannel(channel)
        manager.notify(0, notification)
    }
}