package com.example.khadamni
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.khadamni.models.User
import com.example.khadamni.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {
    //var isRemembred = false
    lateinit var mSharedPref : SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val username: TextView =findViewById(R.id.inputEmail);
        val password: TextView =findViewById(R.id.inputPassword);
        val button: Button = findViewById(R.id.btnlogin);
        val email = username.text.toString().trim()
        val buttonSingUp: TextView = findViewById(R.id.textViewSignUp);
        val mypassword = password.text.toString().trim()
        mSharedPref = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE);
        //isRemembred = mSharedPref.getBoolean("CHECKBOX",false)

       /* if (email.isEmpty()) {
            username.error = "Email required"
            username.requestFocus()
            return
        }

        if (mypassword.isEmpty()) {
            password.error = "Password required"
            password.requestFocus()
            return
        }*/
        button.setOnClickListener {
        var myUser = User()
        myUser.email = username.text.toString()
        myUser.password = username.text.toString()
        val apiUser = RetrofitInstance.api.userLogin(myUser)
        apiUser.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "good", Toast.LENGTH_LONG).show()
                    Log.i("Login User", response.body().toString())
                    println(response);
                        val editor: SharedPreferences.Editor =  mSharedPref.edit()
                        editor.putString("id", response.body()?._id.toString())
                        println( "aaaaa"+response.body()?.email.toString());
                        editor.putString("firstName", response.body()?.firstName.toString())
                        editor.putString("lastName", response.body()?.lastName.toString())
                        editor.putString("emailAddress", response.body()?.email.toString())
                        editor.putString("job", response.body()?.job.toString())
                        editor.putString("urlImg", response.body()?.urlImg.toString())
                        editor.putString("address", response.body()?.address.toString())
                        editor.apply()
                        //Toast.makeText(this,"Information saved !",Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Incorrect email or password",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.i("API RESPONSE", response.toString())
                    Log.i("login response", response.body().toString())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "SERVER ERROR", Toast.LENGTH_LONG).show()
            }
        })
        }




   buttonSingUp.setOnClickListener {
       /* Intent(this, Register::class.java).also {
            startActivity(it)*/
            val intent= Intent(this,Register::class.java)
            startActivity(intent)

    }


    }
}