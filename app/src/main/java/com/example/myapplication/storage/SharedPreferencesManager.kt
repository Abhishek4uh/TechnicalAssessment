package com.example.myapplication.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String,alreadyLoggedIn:Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putBoolean("alreadyLogged",alreadyLoggedIn)
        editor.apply()
    }

    fun getSavedUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun getSavedPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun getExistingUser(): Boolean{
        return sharedPreferences.getBoolean("alreadyLogged", false)
    }

}