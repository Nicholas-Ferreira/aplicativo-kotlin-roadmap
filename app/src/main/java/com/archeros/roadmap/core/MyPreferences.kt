package com.archeros.roadmap.core

import android.content.SharedPreferences

object MyPreferences {
    val PREFERENCES_STORAGE = "ROADMAP_PREFS"

    private fun preferences(): SharedPreferences {
        val context = MyApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREFERENCES_STORAGE, 0)
    }

    fun setBoolean(key: String, value: Boolean) = preferences().edit().putBoolean(key, value).apply()

    fun getBoolean(key: String) = preferences().getBoolean(key, false)

    fun setString(key: String, value: String) = preferences().edit().putString(key, value).apply()

    fun getString(key: String) = preferences().getString(key, "")
}