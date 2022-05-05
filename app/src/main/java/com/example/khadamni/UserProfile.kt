package com.example.khadamni

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout

class UserProfile : AppCompatActivity() {
    lateinit var mSharedPref : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        mSharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        val email: TextView =findViewById(R.id.idEmail);
        email.setText(mSharedPref.getString("EMAIL",""));
        val nom: TextView =findViewById(R.id.idfullname);
        nom.setText(mSharedPref.getString("NOM","No name")+mSharedPref.getString("PRENOM","No prenom"));
        val address: TextView =findViewById(R.id.idAddress);
        address.setText(mSharedPref.getString("ADDRESS",""));
        val job: TextView =findViewById(R.id.idjob);
        job.setText(mSharedPref.getString("JOB","batal"));
        val phone: TextView =findViewById(R.id.idphone);
        phone.setText("+216 "+mSharedPref.getString("PHONE","000000"));
        /*val urlimage: TextView =findViewById(R.id.idUrlImg);
        urlimage.setText("+216 "+mSharedPref.getString("URLIMAGE",""));*/




    }
}