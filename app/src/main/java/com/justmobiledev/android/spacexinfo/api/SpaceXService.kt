package com.justmobiledev.android.spacexinfo.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.justmobiledev.android.spacexinfo.BuildConfig
import com.justmobiledev.android.spacexinfo.api.converters.NullOnEmptyConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.text.DateFormat
import java.util.concurrent.TimeUnit


/**
 * A retrofit service to fetch SpaceX info
 */
interface SpaceXService {
    @GET("v4/company")
    suspend fun getCompanyInfo(
    ): Response<String>

    @GET("v4/crew")
    suspend fun getCrewInfo(
    ): Response<String>

    @GET("v4/rockets")
    suspend fun getRocketInfo(
    ): Response<String>

    @GET("v4/launches/past")
    suspend fun getPastLaunches(
    ): Response<String>

    @GET("v4/launches/upcoming")
    suspend fun getUpcomingLaunches(
    ): Response<String>


}

/**
 * Main entry point for network access.
 */
object Network {
    // Increasing timeouts to avoid socket timeouts
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SPACEX_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val spaceXService = retrofit.create(SpaceXService::class.java)
}
