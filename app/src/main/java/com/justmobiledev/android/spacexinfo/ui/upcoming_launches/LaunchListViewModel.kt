package com.justmobiledev.android.spacexinfo.ui.upcoming_launches

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.justmobiledev.android.spacexinfo.notifications.AlarmReceiver
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import com.justmobiledev.android.spacexinfo.utils.network.NetworkConnectivityChecker
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class LaunchListViewModel(application: Application) : AndroidViewModel(application) {
    private val EXTRA_LAUNCH_DATA = "EXTRA_LAUNCH_DATA"
    private val minute: Long = 60_000L
    private val database = getDatabase(application)
    private val spaceXRepository = SpaceXRepository(database)
    private val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val notificationManager =
        ContextCompat.getSystemService(
            application,
            NotificationManager::class.java
        ) as NotificationManager
    private var pendingIntentList = ArrayList<PendingIntent>()
    private val app = application

    init {
        viewModelScope.launch {
            val isNetworkAvailable = NetworkConnectivityChecker.isNetworkAvailable(application)
            if (isNetworkAvailable) {
                // Fetch launch info
                spaceXRepository.refreshLaunchInfo()
            }
        }
    }

    // Get info from repository
    val launchList = spaceXRepository.launchList

    // Cancel previous alarms
    private fun cancelNotifications() {
        // Cancel notifications
        notificationManager.cancelAll()

        // Cancel alarms
        for (pendingIntent in pendingIntentList) {
            alarmManager.cancel(pendingIntent)
        }
        pendingIntentList.clear()
    }

    private fun calculateTimeTillLaunch(launchDateStr: String): Long {
        // Parse date
        val dateFormat = "MM-dd-yyyy HH:mm:ss"
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        val launchDate = parser.parse(launchDateStr)
        val today = Date()

        return launchDate.time - today.time
    }

    private fun scheduleNotification(triggerTime: Long, launch: DbLaunch) {
        var notifyIntent = Intent(getApplication(), AlarmReceiver::class.java)
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_LAUNCH_DATA, launch)
        notifyIntent.putExtra(EXTRA_LAUNCH_DATA, bundle)

        val notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            UUID.randomUUID().hashCode(),
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            notifyPendingIntent
        )

        // Store pending intent for cancel alarm
        pendingIntentList.add(notifyPendingIntent)
    }

    // Schedule launch notifications
    fun scheduleLaunchNotifications(_launchList: List<DbLaunch>) {
        cancelNotifications()

        if (launchList.value == null) {
            return
        }

        // Iterate through all launches
        for (launch in launchList.value!!) {
            // Calculate time interval till launch
            val launchDateStr = launch.date
            if (launchDateStr == null || launchDateStr.isEmpty()) {
                continue
            }

            // millis until launch
            val timeTillLaunchMs = calculateTimeTillLaunch(launchDateStr)

            // Launch date has passed
            if (timeTillLaunchMs < 0) {
                continue
            }

            // Calculate notification trigger time (launch time - 10 mins)
            val triggerTime = timeTillLaunchMs - (10 * minute)

            // Schedule launch notification
            scheduleNotification(triggerTime, launch)
        }
    }
}