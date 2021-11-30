package com.example.hueapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonAdapter : JSONAdapter = JSONAdapter();
        val button: Button = findViewById(R.id.buttonTest)
        button.setOnClickListener {
            jsonAdapter.getLamps(this)
        }
    }
}