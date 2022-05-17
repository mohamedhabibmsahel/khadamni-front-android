package com.example.khadamni.Controller.jobs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.khadamni.R
import com.example.khadamni.models.Job
import com.example.khadamni.models.User
import com.example.khadamni.services.ApiUser
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobDetailsActivity : AppCompatActivity() {
    lateinit var UserName : TextView
    lateinit var UserLocation : TextView

    lateinit var desription : TextView
    lateinit var usernamePic : ShapeableImageView
    lateinit var pricetext : TextView
    lateinit var buttonaccept : Button
    lateinit var buttonrefuse : Button

    lateinit var to : String
    lateinit var description : String
    lateinit var Price : String
    lateinit var _id : String
    lateinit var idfrom : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)


        val myIntent = intent
         to = myIntent.getStringExtra("to").toString()
         description = myIntent.getStringExtra("description").toString()
         Price = myIntent.getStringExtra("Price").toString()
         _id = myIntent.getStringExtra("_id").toString()
         idfrom = myIntent.getStringExtra("idfrom").toString()


        UserName = findViewById(R.id.UserName)
        UserLocation = findViewById(R.id.UserLocation)
        desription = findViewById(R.id.textView2)
        pricetext = findViewById(R.id.pricetext)
        buttonaccept = findViewById(R.id.button)
        buttonrefuse = findViewById(R.id.button2)
        usernamePic = findViewById(R.id.usernamePic)
        getbyid(to)
        desription.text = description
        pricetext.text = Price
        UserLocation.setOnClickListener {
            val intent = Intent(this, ShowLocationMapBoxActivity::class.java)
            startActivity(intent)
        }
        buttonaccept.setOnClickListener{

            update()
        }

        buttonrefuse.setOnClickListener{

            delete()
        }

    }
    private fun getbyid(id:String ) {

        val apiInterface = ApiUser.create()


        apiInterface.getuserbyid(id).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                val user = response.body()

                if (user != null){
                    UserName.text = "Name : " + user.nom
                    UserLocation.text = "Address : " + user.address

                    Picasso.get().load(user.urlImg).into(usernamePic)
                }else{

                }


            }

            override fun onFailure(call: Call<User>, t: Throwable) {




            }

        })


    }
    private fun update(){

        val apiInterface = ApiUser.create()

        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
//put something inside the map, could be null
//put something inside the map, could be null
        jsonParams["to"] = to
        jsonParams["description"] = description
        jsonParams["price"] =Price.toInt()
        jsonParams["from"] =idfrom
        jsonParams["accepted"] =true




        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(jsonParams).toString()
        )

        apiInterface.updateJob(body,_id).enqueue(object : Callback<Job> {

            override fun onResponse(call: Call<Job>, response: Response<Job>) {

                val user = response.body()

                if (user != null){
                    Toast.makeText(this@JobDetailsActivity, "Job Accepted", Toast.LENGTH_SHORT).show()


                }else{
                    Toast.makeText(this@JobDetailsActivity, "can not Accept Job", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<Job>, t: Throwable) {

                Toast.makeText(this@JobDetailsActivity, "can not Accept Job", Toast.LENGTH_SHORT).show()


            }

        })




    }
    private fun delete( ) {

        val apiInterface = ApiUser.create()


        apiInterface.deleteJob(_id).enqueue(object : Callback<Job> {

            override fun onResponse(call: Call<Job>, response: Response<Job>) {

                val user = response.body()

                if (user != null){
                    Toast.makeText(this@JobDetailsActivity, "Job Deleted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@JobDetailsActivity, "Job can not delete", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<Job>, t: Throwable) {
                Toast.makeText(this@JobDetailsActivity, "Job can not delete", Toast.LENGTH_SHORT).show()



            }

        })


    }
}