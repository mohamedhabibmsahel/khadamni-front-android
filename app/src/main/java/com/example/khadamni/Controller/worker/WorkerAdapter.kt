package com.example.khadamni.Controller.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.User
import com.squareup.picasso.Picasso

class WorkerAdapter (val workerList: MutableList<User> ) : RecyclerView.Adapter<WorkerViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.worker_single_item, parent, false)

        return WorkerViewModel(view)
    }

    override fun onBindViewHolder(holder: WorkerViewModel, position: Int) {
        val usrName = workerList[position].nom+" "+workerList[position].prenom
        val usrRole = workerList[position].job
        val usrNumber = workerList[position].phone
        val usrImage = workerList[position].urlImg
        val usrLocation = workerList[position].address


        holder.userName.text = usrName
        holder.userRole.text = usrRole
        holder.userNumber.text = usrNumber
        holder.userLocation.text = usrLocation
        Picasso.get().load(usrImage).into( holder.userPicture)


    }

    override fun getItemCount() = workerList.size


}