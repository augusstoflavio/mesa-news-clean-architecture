package com.augusto.mesanews.cleanarchitecture.presentation.login.formstate

data class SignupFormState(
        val nameError: Int? = null,
        val usernameError: Int? = null,
        val passwordError: Int? = null
)