package com.justmobiledev.android.spacexinfo.database.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.models.DbRocket


@Dao
interface RocketDao {
    @Query("select * from tbl_rocket")
    fun getRockets(): LiveData<List<DbRocket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crew: List<DbRocket>)
}
