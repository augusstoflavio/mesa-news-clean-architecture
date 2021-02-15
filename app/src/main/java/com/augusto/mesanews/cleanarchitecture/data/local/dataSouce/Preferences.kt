package com.augusto.mesanews.cleanarchitecture.data.local.dataSouce

import android.content.Context

class Preferences(context: Context) {

    companion object {
        private const val USER_NAME = "USER_TOKEN"
        private const val USER_TOKEN = "REFRESH_TOKEN"
    }

    val preferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    fun getToken(): String? {
        return getString(USER_TOKEN)
    }

    fun putToken(token: String) {
        putString(USER_TOKEN, token)
    }

    fun getName(): String? {
        return getString(USER_NAME)
    }

    fun putName(name: String) {
        putString(USER_NAME, name)
    }

    private fun putString(name: String?, value: String?) {
        val edit = preferences.edit()
        edit.putString(name, value!!)
        edit.apply()
    }

    private fun getString(name: String): String? {
        return preferences.getString(name, "")
    }

    fun clear() {
        val edit = preferences.edit()
        edit.clear()
        edit.apply()
    }
}