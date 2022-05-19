package com.example.khadamni.Controller.jobs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.models.User
import com.example.khadamni.services.ApiUser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JobsFragment : Fragment() {

    lateinit var recylcerJob: RecyclerView
    lateinit var recylcerJobAdapter: JobAdapter
    lateinit var animationNoreponse: LottieAnimationView


    var JobListTrue: MutableList<Job> = ArrayList()

    lateinit var mSharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_jobs, container, false)
        mSharedPref =
            this.requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        recylcerJob = rootView.findViewById(R.id.recyclerJobs)

        JobListTrue.clear()
        if(JobListTrue.isEmpty()){
            animationNoreponse.playAnimation()
            animationNoreponse.loop(true)
            animationNoreponse.visibility = View.VISIBLE
        }
        doADD()

        recylcerJobAdapter = JobAdapter(JobListTrue)
        recylcerJob.adapter = recylcerJobAdapter
        recylcerJob.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return rootView

    }

    private fun doADD() {
        val idUser = mSharedPref.getString("ID", "")
        val apiInterface = ApiUser.create()







        apiInterface.AllJobs().enqueue(object : Callback<MutableList<Job>> {

            override fun onResponse(
                call: Call<MutableList<Job>>,
                response: Response<MutableList<Job>>
            ) {

                val user = response.body()

                if (user != null) {
                    for (a in user) {
                        if (a.accepted == false && a.to == idUser) {
                            JobListTrue.add(a)
                        }


                    }
                    recylcerJobAdapter = JobAdapter(ArrayList(JobListTrue.asReversed()))
                    recylcerJob.adapter = recylcerJobAdapter

                } else {
                    animationNoreponse.visibility = View.GONE
                }


            }

            override fun onFailure(call: Call<MutableList<Job>>, t: Throwable) {


            }

        })


    }


}