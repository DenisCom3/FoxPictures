package com.denis.foxpictures

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView



class FoxesAdapter(private val values: List<String>) : RecyclerView.Adapter<FoxesAdapter.FoxViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoxViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fox_list, parent, false)
        return FoxViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoxViewHolder, position: Int) {
        holder.listFoxNumberView.text = values[position]

    }

    override fun getItemCount() :Int{
        return values.size
    }

    class FoxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          var listFoxNumberView: TextView = itemView.findViewById(R.id.tv_fox_item)

        init {
            itemView.setOnClickListener { v:View ->

                val pictureActivityIntent = Intent(itemView.context, PictureActivity::class.java)
                startActivity(itemView.context,pictureActivityIntent,null)

            }
        }

    }

}