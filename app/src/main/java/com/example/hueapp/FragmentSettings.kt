package com.example.hueapp

import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hueapp.databinding.Fragment2Binding

class FragmentSettings : Fragment(R.layout.fragment2) {
    //private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var binding : Fragment2Binding
    public lateinit var sharedPreferencesManager : SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = Fragment2Binding.inflate(inflater,container,false)
        val root = binding.root
        initSetting()
        return root
    }

    fun initSetting() {
        sharedPreferencesManager = SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(context))

        binding.buttonSave.setOnClickListener {
            Log.d("SETTINGS", "Added the settings: IP4" + binding.editTextIP.text.toString() + " and Port: " + binding.editTextPort.text.toString())
            sharedPreferencesManager.AddSetting("IP4", binding.editTextIP.text.toString())
            sharedPreferencesManager.AddSetting("Port", binding.editTextPort.text.toString())
        }
    }
}