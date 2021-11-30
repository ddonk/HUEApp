package com.example.hueapp

import android.content.SharedPreferences

class SharedPreferencesManager(private val settings: SharedPreferences) {
    private val editor: SharedPreferences.Editor

    fun AddSetting(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    init {
        editor = settings.edit()
    }
}