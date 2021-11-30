package com.example.hueapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings = getSharedPreferences("UserInfo", 0)
        val editor = settings.edit()

        val text = findViewById<View>(R.id.testText) as EditText
        val testText = findViewById<TextView>(R.id.testTextView)

        val button: Button = findViewById(R.id.testButton)
        button.setOnClickListener {
            Log.d("TEST", text.getText().toString())
            editor.putString("Username", text.getText().toString())
            editor.commit()
        }



        testText.setText(settings.getString("Username", "").toString())
    }
}