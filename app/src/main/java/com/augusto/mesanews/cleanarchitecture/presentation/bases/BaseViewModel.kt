package com.augusto.mesanews.cleanarchitecture.presentation.bases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.augusto.mesanews.core.domain.entity.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel(private val defaultDispatcher: CoroutineDispatcher): ViewModel() {

    private val scope = viewModelScope

    val error = MutableLiveData<Result.Failure?>()

    val loading = MutableLiveData(false)

    protected fun run(
        call: suspend () -> Unit
    ) {
        scope.launch(defaultDispatcher) {
            loading.postValue(true)
            try {
                call()
            } catch (error: Throwable) {
                notifyFailure(
                    Result.Failure(
                        Result.Error(
                            message = error.message ?: "Erro não identificado"
                        )
                    )
                )
            } finally {
                loading.postValue(false)
            }
        }
    }

    protected fun notifyFailure(resultError: Result.Failure) {
        error.postValue(resultError)
    }
}