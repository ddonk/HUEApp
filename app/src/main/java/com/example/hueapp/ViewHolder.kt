package com.example.hueapp

import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hueapp.R

class RecyclerViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val view: TextView
    fun getView(): TextView {
        return view
    }

    init {
        view = itemView.findViewById(R.id.card_view)
    }
}