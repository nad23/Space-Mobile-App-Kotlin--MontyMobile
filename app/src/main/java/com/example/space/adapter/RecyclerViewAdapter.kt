package com.example.space.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration.get
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.space.FirstFragmentDirections
import com.example.space.R
import com.example.space.SecondFragment
import com.example.space.models.RecyclerData
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get

class RecyclerViewAdapter() :RecyclerView. Adapter<RecyclerViewAdapter.MyViewHolder>() {

 lateinit var action: NavDirections
    var items = ArrayList<RecyclerData>()
    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var launchDate = view.findViewById<TextView>(R.id.launchDate)
        var name = view.findViewById<TextView>(R.id.name)
        var backImage =view.findViewById<ImageView>(R.id.bac_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (items[position].success == "true") {
            holder.launchDate.text = items[position].date_local.toString()
            holder.name.text = items[position].name
            val url =items[position].links.patch.large
            Picasso.get().load(url).into(holder.backImage)
        }

            holder.itemView.setOnClickListener {
                items[position].rocket.let {
                      action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
                }
                Navigation.findNavController(it).navigate(action)
                 }
    }


    override fun getItemCount(): Int {
       return items.size
    }

}