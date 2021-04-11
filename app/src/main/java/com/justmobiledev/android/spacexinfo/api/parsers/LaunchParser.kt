package com.justmobiledev.android.spacexinfo.api.parsers

import android.util.Log
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrew
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCrewContainer
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkLaunch
import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkLaunchContainer
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
// Parse UTC timestamp, e.g. 2021-04-22T10:11:00.000Z
fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

// Convert to local timezone
fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

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

            // Format date to local timeZone
            val dateUtc = jsonObj.getString("date_utc")
            var dateLocal = ""
            if (dateUtc != null) {
                dateLocal =  dateUtc.toDate().formatTo("MM-dd-yyyy HH:mm:ss")
            }

            // todo For testing only: Change launch date to current time + 11 mins
            // todo Then open 'Upcoming launches' to schedule notifications
            /* val id = jsonObj.getString("id")
            if (id == "5fe3af58b3467846b324215f") {
                dateLocal = "04-11-2021 05:40:00"
            }*/

            val launch = NetworkLaunch(
                jsonObj.getString("id"),
                jsonObj.getString("name"),
                details,
                jsonObj.getInt("flight_number").toString(),
                dateLocal,
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