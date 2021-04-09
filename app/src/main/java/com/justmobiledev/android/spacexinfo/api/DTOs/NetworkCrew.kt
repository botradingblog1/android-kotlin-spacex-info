package com.justmobiledev.android.spacexinfo.api.DTOs

import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.squareup.moshi.JsonClass

// DTO for Crew Info
@JsonClass(generateAdapter = true)
data class NetworkCrew(
    val id: String,
    val name: String,
    val agency: String,
    val image: String,
    val status: String,
)

@JsonClass(generateAdapter = true)
data class NetworkCrewContainer(val crew: List<NetworkCrew>)

// Convert Network model into Database model
fun NetworkCrewContainer.asDatabaseModel(): List<DbCrew> {
    return crew.map {
        DbCrew (
            id = it.id,
            name = it.name,
            agency = it.agency,
            image = it.image,
            status = it.status)
    }
}
