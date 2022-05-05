package com.example.khadamni.Controller.jobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.Job


class JobsFragment : Fragment() {

    lateinit var recylcerJob: RecyclerView
    lateinit var recylcerJobAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_jobs, container, false)

        recylcerJob = rootView.findViewById(R.id.recyclerJobs)

        var jobList : MutableList<Job> = ArrayList()

        jobList.add(Job("12154854","Name : Hbib Msahel","Saif",5000,"Chanti benzart"))
        jobList.add(Job("12154854","Name : Gouider Seif","Hbib Msahel",3000,"Dhina in Rades"))


        recylcerJobAdapter = JobAdapter(jobList)
        recylcerJob.adapter = recylcerJobAdapter
        recylcerJob.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)

        return rootView

    }
}