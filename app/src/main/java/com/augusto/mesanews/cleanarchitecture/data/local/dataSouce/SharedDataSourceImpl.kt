package com.augusto.mesanews.cleanarchitecture.data.local.dataSouce

class SharedDataSourceImpl(val preferences: Preferences): SharedPreferencesDataSource {

    override fun isLogged(): Boolean {
        return preferences.getToken() != null
    }

    override fun saveUser(name: String, token: String) {
        preferences.putName(name)
        preferences.putToken(token)
    }

    override fun clear() {
        preferences.clear()
    }

    override fun getToken(): String? {
        return preferences.getToken()
    }
}