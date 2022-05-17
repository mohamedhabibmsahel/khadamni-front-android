package com.example.khadamni.Controller.services

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R

class ServiceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {


    val userFromNameService : TextView
    val serviceTitle : TextView

    init {

        userFromNameService = itemView.findViewById(R.id.userFromNameService)
        serviceTitle = itemView.findViewById(R.id.serviceTitle)
    }
}