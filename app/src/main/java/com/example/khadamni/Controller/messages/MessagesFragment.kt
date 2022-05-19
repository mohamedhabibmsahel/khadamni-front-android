package com.example.khadamni.Controller.messages

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.khadamni.Controller.services.ServiceAdapter
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.services.ApiUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MessagesFragment : Fragment() {

    lateinit var animationNoreponse: LottieAnimationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_messages, container, false)
        animationNoreponse = rootView.findViewById(R.id.animationNoreponse)


            animationNoreponse.playAnimation()
            animationNoreponse.loop(true)
            animationNoreponse.visibility = View.VISIBLE





        return rootView
    }

}