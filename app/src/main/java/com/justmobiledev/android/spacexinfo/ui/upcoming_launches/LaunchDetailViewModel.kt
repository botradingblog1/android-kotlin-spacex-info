package com.justmobiledev.android.spacexinfo.ui.upcoming_launches

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.justmobiledev.android.spacexinfo.database.models.DbRocket
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import com.justmobiledev.android.spacexinfo.utils.network.NetworkConnectivityChecker
import kotlinx.coroutines.launch

class LaunchDetailViewModel(application: Application) : AndroidViewModel(application) {
    var launch = MutableLiveData<DbLaunch>(null)
}