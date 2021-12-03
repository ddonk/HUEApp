package com.example.hueapp

import android.preference.PreferenceManager
import com.example.hueapp.databinding.Fragment2Binding
import org.junit.Assert.*
import org.junit.Test

class FragmentSettingsTest() {
    private lateinit var binding: Fragment2Binding

    @Test
    fun isPreferenceUpdated() {
        val IPString = "192.168.72.69"
        val sharedPreferencesManager = SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(binding.root.context))
        binding.editTextIP.setText(IPString)
        sharedPreferencesManager.AddSetting("Test", binding.editTextIP.text.toString())

        assertEquals(IPString, sharedPreferencesManager.GetSetting("Test") )
    }

    @Test
    fun isPreferenceUpdatedWhenWrongKey() {
        val IPString = "192.168.72.69"
        val sharedPreferencesManager = SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(binding.root.context))
        binding.editTextIP.setText(IPString)
        sharedPreferencesManager.AddSetting("Test", binding.editTextIP.text.toString())

        assertNotEquals(IPString, sharedPreferencesManager.GetSetting("Text") )
    }
}