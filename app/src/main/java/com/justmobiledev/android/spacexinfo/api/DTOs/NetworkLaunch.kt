package com.justmobiledev.android.spacexinfo.api.DTOs

import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.squareup.moshi.JsonClass

// DTO for Launch Info
@JsonClass(generateAdapter = true)
data class NetworkLaunch(
    val id: String,
    val name: String,
    val description: String,
    val flightNumber: String,
    val date: String,
    val image: String,
    val wikipedia: String
)

@JsonClass(generateAdapter = true)
data class NetworkLaunchContainer(val launch: List<NetworkLaunch>)

// Convert Network model into Database model
fun NetworkLaunchContainer.asDatabaseModel(): List<DbLaunch> {
    return launch.map {
        DbLaunch (
            id = it.id,
            name = it.name,
            description = it.description,
            flightNumber = it.flightNumber,
            date = it.date,
            image = it.image,
            wikipedia = it.wikipedia)
    }
}
