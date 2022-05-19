package com.example.khadamni

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.khadamni.Controller.Login
import com.example.khadamni.models.User
import com.example.khadamni.services.ApiUser
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private var selectedImageUri: Uri? = null
    var imagePicker: ImageView? = null
    var minputUrlimg: ImageView? = null

    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_register)
        var buttonSingIn = findViewById<TextView>(R.id.alreadyHaveAccount);
        var buttonSignUp = findViewById<Button>(R.id.btnregister)
        var minputFirstname = findViewById<EditText>(R.id.EditText_show_first_name)
        var minputLastname = findViewById<EditText>(R.id.EditText_show_last_name)
        var minputEmail = findViewById<EditText>(R.id.EditText_show_email)
        var minputPassword = findViewById<EditText>(R.id.EditText_show_password)
        var minputConfirmPassword= findViewById<EditText>(R.id.EditText_show_confirmpassword)
        var minputPhone = findViewById<EditText>(R.id.EditText_show_phone)
        var minputAdress= findViewById<EditText>(R.id.EditText_show_address)
        var minputJob= findViewById<EditText>(R.id.EditText_show_job)
        minputUrlimg= findViewById<ImageView>(R.id.imageView_profile_dp)

        super.onCreate(savedInstanceState)
        minputUrlimg!!.setOnClickListener(View.OnClickListener {
            ImagePicker.with(this)
                .crop()                  //Crop image(Optional), Check Customization for more option
                .compress(1024)          //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)   //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        })
        buttonSignUp.setOnClickListener {
            val firstname = minputFirstname.text.toString().trim()
            val lastname = minputLastname.text.toString().trim()
            val email = minputEmail.text.toString().trim()
            val password = minputPassword.text.toString().trim()
            val conformPassword = minputConfirmPassword.text.toString().trim()
            val phone = minputPhone.text.toString().trim()
            val address = minputAdress.text.toString().trim()
            val job = minputJob.text.toString().trim()


            if (firstname.isEmpty()) {
                minputFirstname.error = "First name required"
                minputFirstname.requestFocus()
                return@setOnClickListener
            }
            if (lastname.isEmpty()) {
                minputLastname.error = "Last name required"
                minputLastname.requestFocus()
                return@setOnClickListener
            }
           if (email.isEmpty()) {
                minputEmail.error = "Email required"
                minputEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                minputPassword.error = "Password required"
                minputPassword.requestFocus()
                return@setOnClickListener
            }
            if (conformPassword.isEmpty()) {
                minputConfirmPassword.error = "Confirm passworfd required"
                minputConfirmPassword.requestFocus()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                minputPhone.error = "Phone number required"
                minputPhone.requestFocus()
                return@setOnClickListener
            }
           if (address.isEmpty()) {
                minputAdress.error = "Address required"
                minputAdress.requestFocus()
                return@setOnClickListener
            }
            if(job.isEmpty()){
                minputJob.error = "Job required"
                minputJob.requestFocus()
                return@setOnClickListener
            }

            val parcelFileDescriptor =
                contentResolver.openFileDescriptor(selectedImageUri!!, "r", null)
                    ?: return@setOnClickListener
            val stream = contentResolver.openInputStream(selectedImageUri!!)
            val request =
                stream?.let { RequestBody.create("image/png".toMediaTypeOrNull(), it.readBytes()) } // read all bytes using kotlin extension
            val photos = request?.let {
                MultipartBody.Part.createFormData(
                    "urlImg",
                    "file.png",
                    it
                )}
            val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()


            data["email"] = RequestBody.create(MultipartBody.FORM, email)
            data["password"] = RequestBody.create(MultipartBody.FORM, password)
            data["nom"] = RequestBody.create(MultipartBody.FORM, lastname)
            data["prenom"] = RequestBody.create(MultipartBody.FORM, firstname)
            data["address"] = RequestBody.create(MultipartBody.FORM, address)
            data["job"] = RequestBody.create(MultipartBody.FORM, job)
            data["phone"] = RequestBody.create(MultipartBody.FORM, phone)

                println(photos);
            val apiUser = ApiUser.create().userSignUp(photos!!,data)
            apiUser.enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@Register, "good", Toast.LENGTH_LONG).show()
                        Log.i("Create User", response.body()!!.toString())




                        val intent = Intent(this@Register, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)




                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error creating User",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("API RESPONSE", response.toString())
                        Log.i("Claim response", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@Register, "SERVER ERROR", Toast.LENGTH_LONG).show()
                }


            })
        }

        buttonSingIn.setOnClickListener {
            val intent= Intent(this, Login::class.java)
            startActivity(intent)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE){
            selectedImageUri = data?.data
            minputUrlimg?.setImageURI(selectedImageUri)

        }
    }
}