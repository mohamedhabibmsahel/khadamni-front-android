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

class ServiceAdapter (val jobsList: MutableList<Job>) : RecyclerView.Adapter<ServiceViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_single_item, parent, false)

        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {


        val price = jobsList[position].Price
        val description = jobsList[position].Description

        val idto = jobsList[position].to
        val idfrom = jobsList[position].from
        val _id = jobsList[position]._id
        holder.serviceTitle.text = "Job price : " + price.toString()
        holder.userFromNameService.text = "Job description : " + description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ServiceDetailsActivity::class.java)
            intent.apply {
                putExtra("_id", _id)
                putExtra("idfrom", idfrom)
                putExtra("to", idto)
                putExtra("description", description)
                putExtra("Price", price.toString())
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = jobsList.size
}