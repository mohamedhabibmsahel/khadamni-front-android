package com.example.khadamni.Controller.services

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R

class ServiceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userPicService : ImageView
    val userFromNameService : TextView
    val serviceTitle : TextView

    init {
        userPicService = itemView.findViewById(R.id.FromImgService)
        userFromNameService = itemView.findViewById(R.id.userFromNameService)
        serviceTitle = itemView.findViewById(R.id.serviceTitle)
    }
}