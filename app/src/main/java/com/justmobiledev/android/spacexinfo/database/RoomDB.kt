package com.justmobiledev.android.spacexinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.justmobiledev.android.spacexinfo.database.DAOs.CompanyDao
import com.justmobiledev.android.spacexinfo.database.DAOs.CrewDao
import com.justmobiledev.android.spacexinfo.database.Models.DbCompany
import com.justmobiledev.android.spacexinfo.database.Models.DbCrew

// Database definitions
@Database(entities = [DbCompany::class, DbCrew::class], version = 2, exportSchema = false)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract val companyDao: CompanyDao
    abstract val crewDao: CrewDao
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