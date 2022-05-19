package com.example.khadamni

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.khadamni.Controller.Login
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.livedata.ChatDomain


class MainActivity : AppCompatActivity() {

    lateinit var mSharedPref : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        mSharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var handler: Handler

        val client =
            ChatClient.Builder("x9dxvxe39a5z", this).logLevel(ChatLogLevel.ALL).build()
        ChatDomain.Builder(client, this).build()


//splash screen 3s
        handler= Handler()
        handler.postDelayed({
            if(mSharedPref.getBoolean("ISREMEMBRED",false)== false){
                val intent= Intent(this, Login::class.java)
                startActivity(intent)
            }else{
                val intent= Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            finish()

        },3000)
    }

}