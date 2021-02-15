package com.augusto.mesanews.cleanarchitecture.data.local.dataSouce

interface SharedPreferencesDataSource {

    fun isLogged(): Boolean
    fun saveUser(name: String, token: String)
    fun clear()
    fun getToken(): String?
}