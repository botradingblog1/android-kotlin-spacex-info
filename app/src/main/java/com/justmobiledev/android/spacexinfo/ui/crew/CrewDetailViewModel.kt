package com.justmobiledev.android.spacexinfo.ui.crew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justmobiledev.android.spacexinfo.database.getDatabase
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.repository.SpaceXRepository
import com.justmobiledev.android.spacexinfo.utils.network.NetworkConnectivityChecker
import kotlinx.coroutines.launch

class CrewDetailViewModel(application: Application) : AndroidViewModel(application) {
    var crewMember = MutableLiveData<DbCrew>(null)
}