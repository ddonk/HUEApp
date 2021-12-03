package com.example.hueapp

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.EindNasaApp.JSONAdapter

class RecyclerAdapter(val jsonAdapter: JSONAdapter) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

   // private lateinit var titles : Array<String>
    private var titles = arrayOf("hue 1 ","hue 2 ","hue 3 ")
    //private lateinit var details : Array<String>
    private var details = arrayOf("hue 1 beschrijving","hue 2 beschrijving","hue 3 beschrijving")
    private var images = intArrayOf(R.drawable.lamp,R.drawable.lamp,R.drawable.lamp)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {


            jsonAdapter.aedfrh245w6tju = jsonAdapter.getLamps()

            Log.d("SIZE", "" +  jsonAdapter.aedfrh245w6tju.size)

            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

            itemView.setOnClickListener (object :View.OnClickListener{
                override fun onClick(v: View?) {
                    val activity=v!!.context as AppCompatActivity
                    val fragmentDetailView=FragmentDetailView()
                    activity.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragmentDetailView).addToBackStack(null).commit()
                }
            } )
//            itemView.setOnClickListener { v: View ->
//                val position : Int = bindingAdapterPosition
//                Toast.makeText(itemView.context, "You clicked on lamp ${position + 1}", Toast.LENGTH_SHORT).show()
//                (activity as MainActivity).replaceFragment(titles[position])
//            }

        }
    }
}