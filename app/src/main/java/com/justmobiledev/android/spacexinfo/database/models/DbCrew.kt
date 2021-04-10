package com.justmobiledev.android.spacexinfo.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_crew")
data class DbCrew constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val agency: String,
    val image: String,
    val status: String): Parcelable