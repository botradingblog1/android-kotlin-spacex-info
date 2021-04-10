package com.justmobiledev.android.spacexinfo.api.parsers

import android.util.Log
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrew
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrewContainer
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkLaunch
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkLaunchContainer
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

object LaunchParser {
    fun parseJsonResult(jsonResult: JSONArray): NetworkLaunchContainer {

        var launchList = ArrayList<NetworkLaunch>()
        for (i in 1..jsonResult.length() -1) {
            val jsonObj = jsonResult.getJSONObject(i)

            // Get image
            val links = jsonObj.getJSONObject("links")
            val patch = links.getJSONObject("patch")
            val imageUrl = patch.getString("large")

            // Get wikipedia link
            var wikipediaUrl = links.getString("wikipedia")
            if (wikipediaUrl.equals("null")) {
                wikipediaUrl = "Not available"
            }

            var details = jsonObj.getString("details")
            if (details.equals("null")) {
                details = "Not available"
            }

            val launch = NetworkLaunch(
                jsonObj.getString("id"),
                jsonObj.getString("name"),
                details,
                jsonObj.getInt("flight_number").toString(),
                jsonObj.getString("date_utc"),
                imageUrl,
                wikipediaUrl,
            )

            launchList.add(launch)
        }

        // Add to container
        val container = NetworkLaunchContainer(launchList)

        return container
    }
}