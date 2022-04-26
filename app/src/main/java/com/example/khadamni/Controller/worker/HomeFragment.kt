package com.example.khadamni.Controller.worker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khadamni.R
import com.example.khadamni.models.Worker

class HomeFragment : Fragment() {
    lateinit var recylcerWorker: RecyclerView
    lateinit var recylcerWorkerAdapter: WorkerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        recylcerWorker = rootView.findViewById(R.id.recyclerWorkers)

        var WorkersList : MutableList<Worker> = ArrayList()

        WorkersList.add(Worker("1","Name : Belgassem","Role : Najar","Location : Ben Arous",R.drawable.juka))
        WorkersList.add(Worker("1","Name : Amor","Role : ingenieur","Location : Megrine",R.drawable.saif))
        WorkersList.add(Worker("1","Name : Salah","Role : Baney","Location : Tunis",R.drawable.saif))
        WorkersList.add(Worker("1","Name : Mohamed","Role : Baney","Location : Rades",R.drawable.juka))
        WorkersList.add(Worker("1","Name : Amir","Role : Dahen","Location : Rades",R.drawable.juka))


        recylcerWorkerAdapter = WorkerAdapter(WorkersList)
        recylcerWorker.adapter = recylcerWorkerAdapter
        recylcerWorker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)

        return rootView

    }
}