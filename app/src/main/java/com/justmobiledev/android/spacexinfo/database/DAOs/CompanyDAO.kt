package com.justmobiledev.android.spacexinfo.database.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justmobiledev.android.spacexinfo.database.models.DbCompany


@Dao
interface CompanyDao {
    @Query("select * from tbl_company")
    fun getCompany(): LiveData<DbCompany>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: DbCompany)
}
