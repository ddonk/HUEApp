package com.example.hueapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.EindNasaApp.JSONAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonAdapter : JSONAdapter = JSONAdapter(this);
        val button: Button = findViewById(R.id.buttonTest)
        button.setOnClickListener {
            jsonAdapter.volleyPost()
            jsonAdapter.getLamps()

            for(lamp in jsonAdapter.lamps) {
                Log.d("JSON", lamp.toString())
            }
        }
    }
}