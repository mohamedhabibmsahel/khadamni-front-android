package com.example.khadamni.Controller.worker

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.services.ApiUser
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject

class CreateJobRequest : AppCompatActivity() {
    private lateinit var txtdescription : EditText
    private lateinit var txtPrice : EditText
    lateinit var btnadd: Button
    lateinit var from: String
    lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_job_request)
        txtdescription = findViewById(R.id.descriptionEt)
        txtPrice = findViewById(R.id.priceEt)
        btnadd = findViewById(R.id.button3)
        val myIntent = intent
        mSharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
         from = myIntent.getStringExtra("from").toString()

        btnadd!!.setOnClickListener {



            if(txtdescription.text.isEmpty()){
                Toast.makeText(this@CreateJobRequest, "Description must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(txtPrice.text.isEmpty()){
                Toast.makeText(this@CreateJobRequest, "Price must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            doADD()

        }
    }
    private fun doADD(){
        val  idUser =  mSharedPref.getString("ID", "")
        val apiInterface = ApiUser.create()



        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
//put something inside the map, could be null
        jsonParams["description"] = txtdescription.text.toString()
        jsonParams["price"] = txtPrice.text.toString().toInt()

        jsonParams["from"] = idUser
        jsonParams["to"] = from
        jsonParams["accepted"] = false

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface.addJob(body).enqueue(object : Callback<Job> {

            override fun onResponse(call: Call<Job>, response: Response<Job>) {

                val user = response.body()

                if (user != null){
                    Toast.makeText(this@CreateJobRequest, "Job Aded", Toast.LENGTH_SHORT).show()



                    finish()
                }else{
                    Toast.makeText(this@CreateJobRequest, "can not Add Job", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<Job>, t: Throwable) {
                Toast.makeText(this@CreateJobRequest, t.message, Toast.LENGTH_SHORT).show()



            }

        })


    }
}