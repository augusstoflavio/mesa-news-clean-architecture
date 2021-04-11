package com.augusto.mesanews.cleanarchitecture.data.validator

import android.util.Patterns

class EmailValidatorImpl: EmailValidator {

    override fun isValid(email: String): Boolean {
        return if (email.contains('@')) {
            val emailAddress = Patterns.EMAIL_ADDRESS
            emailAddress.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }
}