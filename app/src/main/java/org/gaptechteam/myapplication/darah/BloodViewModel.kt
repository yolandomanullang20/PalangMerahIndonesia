package org.gaptechteam.myapplication.darah


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.gaptechteam.myapplication.darah.Blood
import org.gaptechteam.myapplication.darah.network.ApiStatus
import org.gaptechteam.myapplication.darah.network.HewanApi

class BloodViewModel : ViewModel() {
    private val data = MutableLiveData<List<Blood>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
            retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            try {
                data.value = HewanApi.service.getHewan()
                status.value = ApiStatus.SUCCESS

            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }


    fun getData(): LiveData<List<Blood>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}