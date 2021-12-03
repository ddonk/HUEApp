package com.example.hueapp
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hueapp.databinding.Fragment1Binding

class Fragment1 : Fragment (R.layout.fragment1) {

    //private var layoutManager: RecyclerView.LayoutManager? = null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var binding: Fragment1Binding
    private lateinit var lampAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = Fragment1Binding.inflate(inflater,container,false)
        val root = binding.root
        initRecyclerView()
        return root
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            lampAdapter = RecyclerAdapter()
            adapter = lampAdapter
        }
    }
}