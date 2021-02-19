package com.augusto.mesanews.cleanarchitecture.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.UseCases
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import com.augusto.mesanews.cleanarchitecture.presentation.activity.data.Result
import com.augusto.mesanews.cleanarchitecture.presentation.activity.ui.login.LoginFormState

class LoginViewModel(private val sharedPreferencesDataSource: SharedPreferencesDataSource, private val useCases: UseCases) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        run {
            val response = useCases.signin(username, password)
            if (response is Result.Success<*>) {
                sharedPreferencesDataSource.saveUser(username, response.data as String)
                _loginResult.postValue(true)
            } else {
                _loginResult.postValue(false)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
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