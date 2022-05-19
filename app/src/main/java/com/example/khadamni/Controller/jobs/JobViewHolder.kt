package com.example.khadamni.Controller.jobs

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R

class JobViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val userFromName : TextView
    val jobDescription : TextView

    init {

        userFromName = itemView.findViewById(R.id.userFromName)
        jobDescription = itemView.findViewById(R.id.jobDescrition)
    }
}
