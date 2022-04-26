package com.example.khadamni.Controller.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.Worker

class WorkerAdapter (val workerList: MutableList<Worker> ) : RecyclerView.Adapter<WorkerViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.worker_single_item, parent, false)

        return WorkerViewModel(view)
    }

    override fun onBindViewHolder(holder: WorkerViewModel, position: Int) {
        val usrName = workerList[position].nom
        val usrRole = workerList[position].role
        val usrlocation = workerList[position].location
        val usrImage = workerList[position].img


        holder.userName.text = usrName
        holder.userRole.text = usrRole
        holder.userLocation.text = usrlocation
        holder.userPicture.setImageResource(usrImage!!)


    }

    override fun getItemCount() = workerList.size


}