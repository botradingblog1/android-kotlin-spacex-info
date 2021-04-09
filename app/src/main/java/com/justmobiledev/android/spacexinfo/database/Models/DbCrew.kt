package com.justmobiledev.android.spacexinfo.database.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_crew")
data class DbCrew constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val agency: String,
    val image: String,
    val status: String)