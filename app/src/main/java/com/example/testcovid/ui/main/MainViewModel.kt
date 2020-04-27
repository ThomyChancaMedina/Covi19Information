package com.example.testcovid.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testcovid.domain.model.Covid19Imp
import com.example.testcovid.domain.model.LatestImp
import com.example.testcovid.domain.model.LocationImp
import com.example.testcovid.domain.usecase.GetCovid19Case
import com.example.testcovid.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getcovid19Case: GetCovid19Case,
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
        data class Content(val latestImp: Covid19Imp) : UiModel()
        data class Navigation(val location: Covid19Imp) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun onRequestedCovid() {
        launch {
            Log.v(TAG,"thomy ::"+getcovid19Case.invoke().confirmed)
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getcovid19Case.invoke())
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