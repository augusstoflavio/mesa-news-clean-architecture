package com.augusto.mesanews.cleanarchitecture.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.augusto.mesanews.core.domain.entity.Result
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    private val scope = viewModelScope

    val error = MutableLiveData<Result.Error?>().apply {
        value = null
    }

    val loading = MutableLiveData<Boolean>().apply {
        value = false
    }

    protected fun run(
        call: suspend () -> Unit
    ) {
        scope.launch {
            loading.value = true
            try {
                call()
            } catch (error: Throwable) {
                notifyFailure(
                    Result.Error(
                        message = error.message ?: "Erro não identificado"
                    )
                )
            } finally {
                loading.postValue(false)
            }
        }
    }

    protected fun notifyFailure(resultError: Result.Error) {
        error.postValue(resultError)
    }
}