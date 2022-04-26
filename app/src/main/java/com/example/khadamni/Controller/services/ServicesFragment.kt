package com.example.khadamni.Controller.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.Controller.jobs.JobAdapter
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.models.Service


class ServicesFragment : Fragment() {
    lateinit var recylcerService: RecyclerView
    lateinit var recylcerServiceAdapter: ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_services, container, false)

        recylcerService = rootView.findViewById(R.id.recyclerService)

        var ServiceList: MutableList<Service> = ArrayList()

        ServiceList.add(Service("12154854", "Name : Hbib Msahel", "Saif", 5000, "Chanti benzart"))
        ServiceList.add(
            Service(
                "12154854",
                "Name : Gouider Seif",
                "Hbib Msahel",
                3000,
                "Dhina in Rades"
            )
        )


        recylcerServiceAdapter = ServiceAdapter(ServiceList)
        recylcerService.adapter = recylcerServiceAdapter
        recylcerService.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return rootView
    }
}