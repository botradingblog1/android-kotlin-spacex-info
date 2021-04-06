package com.justmobiledev.android.spacexinfo.api


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.justmobiledev.android.spacexinfo.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


/**
 * A retrofit service to fetch SpaceX info
 */
interface SpaceXService {
    @GET("/company")
    suspend fun getCompanyInfo(
    ): Response<String>

    @GET("/crew")
    suspend fun getCrew(
    ): Response<String>

    @GET("/launchpads")
    suspend fun getLaunchPads(
    ): Response<String>

    @GET("/launches/past")
    suspend fun getPastLaunches(
    ): Response<String>

    @GET("/launches/upcoming")
    suspend fun getUpcomingLaunches(
    ): Response<String>

    @GET("/launches/rockets")
    suspend fun getRockets(
    ): Response<String>
}

/**
 * Main entry point for network access. Call like `Network.asteroids.getAsteroidList()`
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
