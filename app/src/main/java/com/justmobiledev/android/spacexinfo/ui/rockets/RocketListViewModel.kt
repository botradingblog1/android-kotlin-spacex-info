package com.justmobiledev.android.spacexinfo.ui.rockets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import com.justmobiledev.android.spacexinfo.utils.network.NetworkConnectivityChecker
import kotlinx.coroutines.launch

class RocketListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val spaceXRepository = SpaceXRepository(database)

    init {
        viewModelScope.launch {
            val isNetworkAvailable = NetworkConnectivityChecker.isNetworkAvailable(application)
            if (isNetworkAvailable) {
                // Fetch rocket info
                spaceXRepository.refreshRocketInfo()
            }
        }
    }

    // Get info from repository
    val rocketList = spaceXRepository.rocketList
}