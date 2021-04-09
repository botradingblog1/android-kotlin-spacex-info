package com.justmobiledev.android.spacexinfo.api.parsers

import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrew
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrewContainer
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

object CrewParser {
    fun parseCrewJsonResult(jsonResult: JSONArray): NetworkCrewContainer {

        var crewList = ArrayList<NetworkCrew>()
        for (i in 1..jsonResult.length() -1) {
            val jsonObj = jsonResult.getJSONObject(i)

            val crew = NetworkCrew(
                jsonObj.getString("id"),
                jsonObj.getString("name"),
                jsonObj.getString("agency"),
                jsonObj.getString("image"),
                jsonObj.getString("status"),
            )

            crewList.add(crew)
        }

        // Add to container
        val container = NetworkCrewContainer(crewList)

        return container
    }
}