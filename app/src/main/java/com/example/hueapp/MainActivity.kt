package com.example.hueapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hueapp.databinding.ActivityMainBinding
import android.util.Log
import android.widget.*
import com.example.EindNasaApp.JSONAdapter
import android.widget.EditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Btn1.setOnClickListener{
            replaceFragment(Fragment1())
        }

        binding.Btn2.setOnClickListener{
            replaceFragment(Fragment2())
        }
    }

    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
        setContentView(R.layout.activity_main)

        
    // START JSON
//        onCreate(lamp)
        val jsonAdapter : JSONAdapter = JSONAdapter(this);
        val button: Button = findViewById(R.id.buttonGetRequest)
        button.setOnClickListener {
            jsonAdapter.getLamps()

            for(lamp in jsonAdapter.lamps) {
                Log.d("JSON", lamp.toString())
            }
        }

        val button1: Button = findViewById(R.id.buttonPutRequest)
        button1.setOnClickListener {
            val randomNumber = Random.nextInt(100)
            val randomNumber1 = Random.nextInt(254)
            val randomNumber2 = Random.nextInt(65535)
            val randomNumber3 = Random.nextInt(254)

            val lampState = LampState(true, randomNumber1, randomNumber2, randomNumber3)
            val lamp = Lamp(1, "Daan" + randomNumber, "Piet", lampState)
            jsonAdapter.PutRequest(lamp)
        }
    }

    fun onCreate(lamp : Lamp) {

        val idText = findViewById<TextView>(R.id.textViewIDName)
        val nameText = findViewById<EditText>(R.id.editTextName)
        val satBar = findViewById<SeekBar>(R.id.satBar)
        val briBar = findViewById<SeekBar>(R.id.brightnessBar)
        val hueBar = findViewById<SeekBar>(R.id.hueBar)
        val onSwitch = findViewById<Switch>(R.id.switchOn)
        val updateButton = findViewById<Button>(R.id.updateButton)

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

            val toast = Toast.makeText(this, text, duration)
            toast.show()
        }
    }
}