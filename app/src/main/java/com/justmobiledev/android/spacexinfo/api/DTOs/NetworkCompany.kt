package com.justmobiledev.android.spacexinfo.api.DTOs

import com.justmobiledev.android.spacexinfo.database.Models.DbCompany
import com.squareup.moshi.JsonClass

// DTO for Company Info
@JsonClass(generateAdapter = true)
data class NetworkCompany(
    val id: String,
    val name: String,
    val summary: String,
    val founder: String,
    val founded: String,
    val employees: Double,
    val ceo: String,
    val cto: String,
    val coo: String,
    val cto_propulsion: String
)


/**
 * Convert Network Company Info model into Database model
 */
fun NetworkCompany.asDatabaseModel(): DbCompany {
   return DbCompany (
        id = this.id,
        name = this.name,
        summary = this.summary,
        founder = this.founder,
        founded = this.founded,
        employees = this.employees,
        ceo = this.ceo,
        coo = this.coo,
        cto = this.cto,
        cto_propulsion = this.cto_propulsion,
    )
}
