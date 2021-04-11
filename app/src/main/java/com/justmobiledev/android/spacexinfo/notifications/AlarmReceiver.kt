package com.justmobiledev.android.spacexinfo.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import timber.log.Timber

class AlarmReceiver: BroadcastReceiver() {
    private val EXTRA_LAUNCH_DATA = "EXTRA_LAUNCH_DATA"

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        val bundle = intent.getBundleExtra(EXTRA_LAUNCH_DATA)
        val launch = bundle?.getParcelable<DbLaunch>(EXTRA_LAUNCH_DATA)

        if (launch != null) {
            notificationManager.sendNotification(
                context,
                launch
            )
        }
    }
}