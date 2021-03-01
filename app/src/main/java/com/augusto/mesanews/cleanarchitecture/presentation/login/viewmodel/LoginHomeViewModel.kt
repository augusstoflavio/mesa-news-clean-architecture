package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource

class LoginHomeViewModel(private val sharedPreferencesDataSource: SharedPreferencesDataSource): ViewModel() {

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> = _isLogged

    fun checkIsLogged() {
        _isLogged.value = sharedPreferencesDataSource.isLogged()
    }
}