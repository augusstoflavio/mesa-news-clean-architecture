package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.validator.EmailValidator
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.formstate.SignupFormState
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.useCase.Signup
import kotlinx.coroutines.CoroutineDispatcher

class SignupViewModel(
        private val signupUseCase: Signup,
        private val emailValidator: EmailValidator,
        private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel(defaultDispatcher) {

    private val _signupFormState= MutableLiveData<SignupFormState>()
    val signinFormState: LiveData<SignupFormState> = _signupFormState

    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> = _signupResult

    fun signup(name: String, username: String, password: String) {
        if (!validInputs(name, username, password)) {
            return
        }

        run {
            when (val response = signupUseCase(name, username, password)) {
                is Result.Success -> {
                    _signupResult.postValue(true)
                }
                is Result.Failure -> {
                    _signupResult.postValue(false)
                    notifyFailure(response)
                }
            }
        }
    }

    private fun validInputs(name: String, username: String, password: String): Boolean {
        if (name.isEmpty()) {
            _signupFormState.value = SignupFormState(nameError = R.string.invalid_name)
            return false
        } else if (!emailValidator.isValid(username)) {
            _signupFormState.value = SignupFormState(usernameError = R.string.invalid_username)
            return false
        } else if (!isPasswordValid(password)) {
            _signupFormState.value = SignupFormState(passwordError = R.string.invalid_password)
            return false
        }

        return true
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}