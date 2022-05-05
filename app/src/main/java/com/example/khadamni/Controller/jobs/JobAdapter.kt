package com.example.khadamni.Controller.jobs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.Job

class JobAdapter(val jobsList: MutableList<Job>) : RecyclerView.Adapter<JobViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_single_item, parent, false)

        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val usrName = jobsList[position].from
        val description = jobsList[position].Description

        holder.userPic.setImageResource(R.drawable.juka)
        holder.jobDescription.text = description
        holder.userFromName.text = usrName

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, JobDetailsActivity::class.java)
            intent.apply {
                putExtra("NAME", usrName)
                putExtra("DESCRIPTION", description)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount() = jobsList.size
}