package com.justmobiledev.android.spacexinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.justmobiledev.android.spacexinfo.database.DAOs.CompanyDao
import com.justmobiledev.android.spacexinfo.database.DAOs.CrewDao
import com.justmobiledev.android.spacexinfo.database.DAOs.RocketDao
import com.justmobiledev.android.spacexinfo.database.models.DbCompany
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.models.DbRocket

// Database definitions
@Database(entities = [DbCompany::class, DbCrew::class, DbRocket::class], version = 3, exportSchema = false)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract val companyDao: CompanyDao
    abstract val crewDao: CrewDao
    abstract val rocketDao: RocketDao
}

// Global Room database instance
private lateinit var INSTANCE: SpaceXDatabase
fun getDatabase(context: Context): SpaceXDatabase {
    synchronized(SpaceXDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                SpaceXDatabase::class.java,
                "spacex_db")
                .fallbackToDestructiveMigration().build()
        }

        return INSTANCE
    }
}