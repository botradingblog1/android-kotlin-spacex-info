package com.justmobiledev.android.spacexinfo.repository

import androidx.lifecycle.LiveData
import com.justmobiledev.android.spacexinfo.api.DTOs.asDatabaseModel
import com.justmobiledev.android.spacexinfo.api.Network
import com.justmobiledev.android.spacexinfo.api.parsers.CompanyInfoParser
import com.justmobiledev.android.spacexinfo.api.parsers.CrewParser
import com.justmobiledev.android.spacexinfo.api.parsers.RocketParser
import com.justmobiledev.android.spacexinfo.database.models.DbCompany
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.SpaceXDatabase
import com.justmobiledev.android.spacexinfo.database.models.DbRocket
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

class SpaceXRepository(private val database: SpaceXDatabase) {
    val companyInfo: LiveData<DbCompany> = database.companyDao.getCompany()

    val crewList: LiveData<List<DbCrew>> = database.crewDao.getCrew()

    val rocketList: LiveData<List<DbRocket>> = database.rocketDao.getRockets()

    suspend fun refreshCompanyInfo() {
        val response = Network.spaceXService.getCompanyInfo()
        if (response.isSuccessful && response.body() != null) {
            try {
                val jsonObject = JSONObject(response.body())

                // Convert JSON to network objects
                val companyInfo = CompanyInfoParser.parseCompanyInfoJsonResult(jsonObject)
                Timber.d(companyInfo.toString())
                Timber.d("Company info API returned result")

                // Convert to database objects
                val dbCompany = companyInfo.asDatabaseModel()

                // Insert records into db
                database.companyDao.insert(dbCompany)

                Timber.d("Refresh Company info complete")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            Timber.e("getCompanyInfo call not successful: %s", response.errorBody().toString())
        }
    }

    suspend fun refreshCrewInfo() {
        val response = Network.spaceXService.getCrewInfo()
        if (response.isSuccessful && response.body() != null) {
            try {
                val jsonResult = JSONArray(response.body())

                // Convert JSON to network objects
                val crewList = CrewParser.parseCrewJsonResult(jsonResult)
                Timber.d(crewList.toString())
                Timber.d("Crew info API returned result")

                // Convert to database objects
                val dbCrewList = crewList.asDatabaseModel()

                // Insert records into db
                database.crewDao.insert(dbCrewList)

                Timber.d("Refresh Crew info complete")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            Timber.e("getCompanyInfo call not successful: %s", response.errorBody().toString())
        }
    }

    suspend fun refreshRocketInfo() {
        val response = Network.spaceXService.getRocketInfo()
        if (response.isSuccessful && response.body() != null) {
            try {
                val jsonResult = JSONArray(response.body())

                // Convert JSON to network objects
                val rocketList = RocketParser.parseJsonResult(jsonResult)
                Timber.d(rocketList.toString())
                Timber.d("Rocket info API returned result")

                // Convert to database objects
                val dbRocketList = rocketList.asDatabaseModel()

                // Insert records into db
                database.rocketDao.insert(dbRocketList)

                Timber.d("Refresh Rocket info complete")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            Timber.e("getCompanyInfo call not successful: %s", response.errorBody().toString())
        }
    }
}