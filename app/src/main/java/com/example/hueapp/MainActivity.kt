package com.example.hueapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.hueapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Btn1.setOnClickListener{
            replaceFragment(FragmentRecycleView())
            Log.d("Knop 1","hoi")
        }

        binding.Btn2.setOnClickListener{
            replaceFragment(FragmentSettings())
            Log.d("Knop 2","hoi")
        }
    }

    public fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
        //setContentView(R.layout.activity_main)
    }

    public fun replaceFragment(position : Array<String>) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, FragmentDetailView())
        fragmentTransaction.commit()
    }
}