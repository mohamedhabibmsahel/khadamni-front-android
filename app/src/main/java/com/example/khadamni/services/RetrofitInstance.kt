package com.example.khadamni.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ApiUser by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.3:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiUser::class.java)
    }
}