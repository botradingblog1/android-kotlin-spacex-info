package com.justmobiledev.android.spacexinfo.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.justmobiledev.android.spacexinfo.MainActivity
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch


// Create notification channel
fun createChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.notification_channel_name),

            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                setShowBadge(false)
            }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = context.getString(R.string.notification_channel_description)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

// Send notification
fun NotificationManager.sendNotification(context: Context, launch: DbLaunch) {

    // Create custom notification info based on launch data
    val id = launch.id
    val name = launch.name
    val description = context.getString(R.string.notification_description)

    val contentIntent = Intent(context, MainActivity::class.java)
    contentIntent.putExtra(EXTRA_LAUNCH_ID, id)

    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val rocketImage = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_rocket_dark
    )

    // Build notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(name)
        .setContentText(description)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(contentPendingIntent)
        .setSmallIcon(R.drawable.ic_rocket_dark)
        .setLargeIcon(rocketImage)

    notify(NOTIFICATION_ID, builder.build())
}

private const val NOTIFICATION_ID = 23
private const val CHANNEL_ID = "SpaceXNotificationChannel"
const val EXTRA_LAUNCH_ID = "EXTRA_LAUNCH_ID"