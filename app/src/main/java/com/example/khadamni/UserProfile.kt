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
        email.setText(mSharedPref.getString("emailAddress",""));


    }
}