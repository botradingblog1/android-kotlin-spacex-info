package com.justmobiledev.android.spacexinfo.database.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_company")
data class DbCompany constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val summary: String,
    val founder: String,
    val founded: String,
    val employees: Double,
    val ceo: String,
    val cto: String,
    val coo: String,
    val cto_propulsion: String)