package com.example.khadamni.Controller.jobs

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.models.User
import com.example.khadamni.services.ApiUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobAdapter(val jobsList: MutableList<Job>) : RecyclerView.Adapter<JobViewHolder>() {

    lateinit var Usernow : User
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_single_item, parent, false)

        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val price = jobsList[position].Price
        val description = jobsList[position].Description

        val idto = jobsList[position].to
        val idfrom = jobsList[position].from
        val _id = jobsList[position]._id
        holder.jobDescription.text = "Job description : " + description
        holder.userFromName.text = "Job price : " + price.toString()


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, JobDetailsActivity::class.java)
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
