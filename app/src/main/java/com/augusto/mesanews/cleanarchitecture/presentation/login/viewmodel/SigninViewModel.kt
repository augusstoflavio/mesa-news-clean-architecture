package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import com.augusto.mesanews.cleanarchitecture.data.validator.EmailValidator
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.formstate.SigninFormState
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.useCase.Signin
import kotlinx.coroutines.CoroutineDispatcher

class SigninViewModel(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    private val signin: Signin,
    private val emailValidator: EmailValidator,
    private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel(defaultDispatcher) {

    private val _loginForm = MutableLiveData<SigninFormState>()
    val signinFormState: LiveData<SigninFormState> = _loginForm

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        if (!loginDataChanged(username, password)) {
            return
        }

        run {
            when (val response = signin(username, password)) {
                is Result.Success -> {
                    sharedPreferencesDataSource.saveUser(username, response.data)
                    _loginResult.postValue(true)
                }
                is Result.Failure -> {
                    _loginResult.postValue(false)
                    notifyFailure(response)
                }
            }
        }
    }

    private fun loginDataChanged(username: String, password: String): Boolean {
        if (!emailValidator.isValid(username)) {
            _loginForm.value = SigninFormState(usernameError = R.string.invalid_username)
            return false
        } else if (!isPasswordValid(password)) {
            _loginForm.value = SigninFormState(passwordError = R.string.invalid_password)
            return false
        }

        return true
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}