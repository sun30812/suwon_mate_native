package com.sn30.suwonuniv.info.suwonmate_native.models

import android.content.Context
import android.content.SharedPreferences

class SettingStore(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("main_pref", Context.MODE_PRIVATE)
    fun getString(key: String, defaultValue: String) = pref.getString(key, defaultValue).toString()
    fun setString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }
    fun getBool(key: String, defaultValue: Boolean) = pref.getBoolean(key, defaultValue)
    fun setBool(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }
    fun getInt(key: String, defaultValue: Int) = pref.getInt(key, defaultValue)
    fun setInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }
}
