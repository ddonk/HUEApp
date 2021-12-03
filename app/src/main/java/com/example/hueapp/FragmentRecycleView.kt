package com.example.hueapp
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.EindNasaApp.JSONAdapter

import com.example.hueapp.databinding.FragmentRecycleviewBinding

class FragmentRecycleView : Fragment (R.layout.fragment_recycleview) {

    //private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var binding: FragmentRecycleviewBinding
    private lateinit var lampAdapter: RecyclerAdapter
    private lateinit var testingClass: TestingClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentRecycleviewBinding.inflate(inflater,container,false)
        val root = binding.root
        initRecyclerView()
        return root
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            testingClass = TestingClass(context, SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(context)))

            lampAdapter = RecyclerAdapter(testingClass, binding.refreshButton, binding.refreshButton1)
            adapter = lampAdapter
        }
    }
}