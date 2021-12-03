package com.example.hueapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

import com.example.hueapp.databinding.FragmentLampcontrolBinding
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class FragmentDetailView : Fragment(R.layout.fragment_lampcontrol) {

    private lateinit var binding: FragmentLampcontrolBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {

        this.binding = FragmentLampcontrolBinding.inflate(inflater,container,false)
        val root = binding.root

        val randomNumber = Random.nextInt(100)
        val randomNumber1 = Random.nextInt(254)
        val randomNumber2 = Random.nextInt(65535)
        val randomNumber3 = Random.nextInt(254)
        val lampState = LampState(true, randomNumber1, randomNumber2, randomNumber3)
        val lamp = Lamp(1, "Daan" + randomNumber, "Piet", lampState)
        initDetailView(lamp)
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