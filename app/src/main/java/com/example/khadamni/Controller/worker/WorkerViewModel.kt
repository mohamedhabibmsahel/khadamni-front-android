package com.example.khadamni.Controller.worker

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R

class WorkerViewModel (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userPicture : ImageView
    val userName : TextView
    val userRole : TextView
    val userNumber : TextView
    val userLocation: TextView
    val hirejob : Button

    init {
        userPicture = itemView.findViewById(R.id.usernamePic)
        userName = itemView.findViewById(R.id.UserName)
        userRole = itemView.findViewById(R.id.UserRole)
        userNumber = itemView.findViewById(R.id.UserPhoneNumber)
        userLocation = itemView.findViewById(R.id.UserLocation)
        hirejob = itemView.findViewById(R.id.hireforjobBtn)
    }
}