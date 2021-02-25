package com.augusto.mesanews.cleanarchitecture.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import com.augusto.mesanews.cleanarchitecture.presentation.activity.ui.login.LoginFormState
import com.augusto.mesanews.core.domain.entity.Result

class LoginViewModel(private val sharedPreferencesDataSource: SharedPreferencesDataSource, private val useCases: UseCases) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        if (!loginDataChanged(username, password)) {
            return
        }

        run {
            when (val response = useCases.signin(username, password)) {
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
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
            return false
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
            return false
        }

        return true
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}