package com.talhadengiz.tomurcuk.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {

    companion object {
        private const val sharedPreference = "sharedPreference"
        private const val login = "login"
    }

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(
            sharedPreference,
            Context.MODE_PRIVATE
        )
    }

    fun saveLogin(isLogin:Boolean) {
        sharedPref.edit().putBoolean(login, isLogin).apply()
    }

    fun isLogin(): Boolean {
        return sharedPref.getBoolean(login, false)
    }
}