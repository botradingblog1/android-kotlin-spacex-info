package com.justmobiledev.android.spacexinfo.api.DTOs

import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.models.DbRocket
import com.squareup.moshi.JsonClass

// DTO for Rocket Info
@JsonClass(generateAdapter = true)
data class NetworkRocket(
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
    val image: String,
)

@JsonClass(generateAdapter = true)
data class NetworkRocketContainer(val crew: List<NetworkRocket>)

// Convert Network model into Database model
fun NetworkRocketContainer.asDatabaseModel(): List<DbRocket> {
    return crew.map {
        DbRocket (
            id = it.id,
             name = it.name,
             description = it.description,
             active = it.active,
             stages = it.stages,
             boosters = it.boosters,
             costPerLaunch = it.costPerLaunch,
             successRatePct = it.successRatePct,
             firstFlight = it.firstFlight,
             country = it.country,
             wikipedia = it.wikipedia,
             image = it.image)
    }
}
