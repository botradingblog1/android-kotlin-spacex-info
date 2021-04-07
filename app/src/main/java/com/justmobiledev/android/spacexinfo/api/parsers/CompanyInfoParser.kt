package com.justmobiledev.android.spacexinfo.api.parsers

import com.justmobiledev.android.spacexinfo.api.DTOs.NetworkCompany
import org.json.JSONObject
import kotlin.collections.ArrayList

object CompanyInfoParser {
    fun parseCompanyInfoJsonResult(jsonResult: JSONObject): NetworkCompany {

        val company = NetworkCompany(
            jsonResult.getString("id"),
            jsonResult.getString("name"),
            jsonResult.getString("summary"),
            jsonResult.getString("founder"),
            jsonResult.getString("founded"),
            jsonResult.getDouble("employees"),
            jsonResult.getString("ceo"),
            jsonResult.getString("cto"),
            jsonResult.getString("coo"),
            jsonResult.getString("cto_propulsion"))

        return company
    }
}