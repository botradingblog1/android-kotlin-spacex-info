package com.justmobiledev.android.spacexinfo.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import retrofit2.HttpException

class RefreshWorkManager(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = SpaceXRepository(database)
        return try {
            // Fetch data from SpaceX API
            repository.refreshCompanyInfo()
            repository.refreshCrewInfo()
            repository.refreshRocketInfo()
            repository.refreshLaunchInfo()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshWorkManager"
    }
}