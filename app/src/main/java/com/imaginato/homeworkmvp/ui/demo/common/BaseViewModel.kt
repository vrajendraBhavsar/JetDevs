package com.imaginato.homeworkmvp.ui.demo.common

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imaginato.homeworkmvp.common.enums.NetworkStatus

open class BaseViewModel : ViewModel() {

    private var isFirst = true
    /*private var _networkStatusLiveEvent: SingleLiveEvent<NetworkStatus> =
        SingleLiveEvent()
    val networkStatusLiveEvent: MutableLiveData<NetworkStatus> get() = _networkStatusLiveEvent*/

    /**
     * Only can call once per lifecycle
     * @param multipleTimes (OPTIONAL) set it to true to make multiple call capability
     */
    @CallSuper
    open fun loadPage(multipleTimes: Boolean = false): Boolean {
        if (isFirst) {
            isFirst = false
            return true
        }

        return isFirst || multipleTimes
    }

    /*fun setNetworkStatus(networkStatus: NetworkStatus) {
        _networkStatusLiveEvent.value = networkStatus
    }*/

    override fun onCleared() {
        super.onCleared()
    }
}