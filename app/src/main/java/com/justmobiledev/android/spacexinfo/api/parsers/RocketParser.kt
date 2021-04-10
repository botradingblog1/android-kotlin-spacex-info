package com.justmobiledev.android.spacexinfo.api.parsers

import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrew
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrewContainer
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkRocket
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkRocketContainer
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

object RocketParser {
    fun parseJsonResult(jsonResult: JSONArray): NetworkRocketContainer {

        var rocketList = ArrayList<NetworkRocket>()
        for (i in 1..jsonResult.length() -1) {
            val jsonObj = jsonResult.getJSONObject(i)

            // Get image
            val imageList = jsonObj.getJSONArray("flickr_images")
            var imageUrl = ""
            if (imageList.length() > 0) {
                imageUrl = imageList[0] as String
            }

            val rocket = NetworkRocket(
                jsonObj.getString("id"),
                jsonObj.getString("name"),
                jsonObj.getString("description"),
                jsonObj.getString("active"),
                jsonObj.getInt("stages"),
                jsonObj.getInt("boosters"),
                jsonObj.getInt("cost_per_launch"),
                jsonObj.getInt("success_rate_pct"),
                jsonObj.getString("first_flight"),
                jsonObj.getString("country"),
                jsonObj.getString("wikipedia"),
                imageUrl,
            )

            rocketList.add(rocket)
        }

        // Add to container
        val container = NetworkRocketContainer(rocketList)

        return container
    }
}