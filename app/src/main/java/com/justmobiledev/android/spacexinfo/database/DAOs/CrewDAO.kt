package com.justmobiledev.android.spacexinfo.database.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justmobiledev.android.spacexinfo.database.Models.DbCompany
import com.justmobiledev.android.spacexinfo.database.Models.DbCrew


@Dao
interface CrewDao {
    @Query("select * from tbl_crew")
    fun getCrew(): LiveData<List<DbCrew>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crew: List<DbCrew>)
}
