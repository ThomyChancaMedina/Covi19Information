package com.example.testcovid.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp
import com.example.testcovid.domain.usecase.GetLatestCase
import com.example.testcovid.ui.common.ScopedViewModel
//import com.example.testcovid.utils.UiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLatestCase: GetLatestCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val latestImp: LatestImp) : UiModel()
        data class Navigation(val location: LocationImp) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun onRequestedCovid() {
        launch {
            Log.v(TAG,"thomy ::"+getLatestCase.invoke().confirmed)
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getLatestCase.invoke())
        }
    }

    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }


}