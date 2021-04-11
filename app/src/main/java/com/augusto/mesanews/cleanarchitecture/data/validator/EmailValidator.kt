package com.augusto.mesanews.cleanarchitecture.data.validator

interface EmailValidator {

    fun isValid(email: String): Boolean
}