package com.justmobiledev.android.spacexinfo.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import com.justmobiledev.android.spacexinfo.utils.network.NetworkConnectivityChecker
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val spaceXRepository = SpaceXRepository(database)

    init {
        viewModelScope.launch {
            val isNetworkAvailable = NetworkConnectivityChecker.isNetworkAvailable(application)
            if (isNetworkAvailable) {
                // Fetch company info
                spaceXRepository.refreshCompanyInfo()
            }
        }
    }

    // Get info from repository
    val companyInfo = spaceXRepository.companyInfo

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}