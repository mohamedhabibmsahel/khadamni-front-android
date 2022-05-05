package com.example.khadamni.Controller.services

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.Controller.jobs.JobDetailsActivity
import com.example.khadamni.Controller.jobs.JobViewHolder
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.models.Service

class ServiceAdapter (val servicesList: MutableList<Service>) : RecyclerView.Adapter<ServiceViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_single_item, parent, false)

        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val usrName = servicesList[position].from
        val description = servicesList[position].Description

        holder.userPicService.setImageResource(R.drawable.juka)
        holder.serviceTitle.text = description
        holder.userFromNameService.text = usrName

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ServiceDetailsActivity::class.java)
            intent.apply {
                putExtra("NAME", usrName)
                putExtra("DESCRIPTION", description)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = servicesList.size
}