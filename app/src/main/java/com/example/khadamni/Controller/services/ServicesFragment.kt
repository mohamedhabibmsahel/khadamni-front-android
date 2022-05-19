package com.example.khadamni.Controller.services

import android.content.Context
import android.content.SharedPreferences
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
import com.example.khadamni.services.ApiUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServicesFragment : Fragment() {
    lateinit var recylcerService: RecyclerView
    lateinit var recylcerServiceAdapter: ServiceAdapter
    lateinit var mSharedPref: SharedPreferences
    var JobListTrue : MutableList<Job> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_services, container, false)
        mSharedPref =  this.requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        recylcerService = rootView.findViewById(R.id.recyclerService)


        JobListTrue.clear()
        doADD()


        recylcerServiceAdapter = ServiceAdapter(JobListTrue)
        recylcerService.adapter = recylcerServiceAdapter
        recylcerService.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return rootView
    }
    private fun doADD(){
        val  idUser =  mSharedPref.getString("ID", "")
        val apiInterface = ApiUser.create()







        apiInterface.AllJobs().enqueue(object : Callback<MutableList<Job>> {

            override fun onResponse(call: Call<MutableList<Job>>, response: Response<MutableList<Job>>) {

                val user = response.body()

                if (user != null){
                    for(a in user){
                        if(a.accepted == true && (a.to == idUser || a.from == idUser ) ){
                            JobListTrue.add(a)
                        }





                    }
                    recylcerServiceAdapter = ServiceAdapter(ArrayList(JobListTrue.asReversed()))
                    recylcerService.adapter = recylcerServiceAdapter


                }else{

                }


            }

            override fun onFailure(call: Call<MutableList<Job>>, t: Throwable) {




            }

        })




    }
}