package com.justmobiledev.android.spacexinfo.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_rocket")
data class DbRocket constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val active: String,
    val stages: Int,
    val boosters: Int,
    val costPerLaunch: Int,
    val successRatePct: Int,
    val firstFlight: String,
    val country: String,
    val wikipedia: String,
    val image: String): Parcelable