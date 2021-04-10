package com.justmobiledev.android.spacexinfo.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_launch")
data class DbLaunch constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val flightNumber: String,
    val date: String,
    val image: String,
    val wikipedia: String): Parcelable
