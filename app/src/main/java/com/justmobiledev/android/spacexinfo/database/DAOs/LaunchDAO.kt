package com.justmobiledev.android.spacexinfo.database.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.justmobiledev.android.spacexinfo.database.models.DbRocket


@Dao
interface LaunchDao {
    @Query("select * from tbl_launch")
    fun getLaunches(): LiveData<List<DbLaunch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(launch: List<DbLaunch>)
}
