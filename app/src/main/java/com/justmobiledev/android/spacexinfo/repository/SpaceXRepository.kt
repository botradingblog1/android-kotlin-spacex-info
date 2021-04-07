package com.justmobiledev.android.spacexinfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.justmobiledev.android.spacexinfo.api.DTOs.asDatabaseModel
import com.justmobiledev.android.spacexinfo.api.Network
import com.justmobiledev.android.spacexinfo.api.parsers.CompanyInfoParser
import com.justmobiledev.android.spacexinfo.database.Models.DbCompany
import com.justmobiledev.android.spacexinfo.database.SpaceXDatabase
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

class SpaceXRepository(private val database: SpaceXDatabase) {
    val companyInfo: LiveData<DbCompany> = database.companyDao.getCompany()

    suspend fun refreshCompanyInfo() {

        val response = Network.spaceXService.getCompanyInfo()

        if (response.isSuccessful && response.body() != null) {
            try {
                val jsonObject = JSONObject(response.body())

                // Convert JSON to domain objects
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
}