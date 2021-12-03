package com.example.hueapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.hueapp.databinding.FragmentLampControlBinding

class FragmentDetailView : Fragment(R.layout.fragment_lamp_control) {

    private lateinit var binding: FragmentLampControlBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {

        this.binding = FragmentLampControlBinding.inflate(inflater,container,false)
        val root = binding.root

//        initDetailView()
        return root
    }

    private fun initDetailView(lamp : Lamp) {
        val idText = binding.textViewIDName
        val nameText = binding.editTextName
        val satBar = binding.satBar
        val briBar = binding.brightnessBar
        val hueBar = binding.hueBar
        val onSwitch = binding.switchOn
        val updateButton = binding.updateButton

        idText.text = lamp.modelid
        nameText.setText(lamp.name)

        val state = lamp.state

        satBar.setProgress(state.sat)
        briBar.setProgress(state.brightness)
        hueBar.setProgress(state.hue)
        onSwitch.isChecked = state.on
        updateButton.setOnClickListener {
            Log.d("Lamp", "Button working")
            val text = "Checking button"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }
}