package com.example.khadamni

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.khadamni.Controller.Login
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Register : AppCompatActivity() {
    private var selectedImageUri: Uri? = null
    var imagePicker: ImageView? = null
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
        var minputUrlimg= findViewById<ImageView>(R.id.imageView_profile_dp)











        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        minputUrlimg.setOnClickListener(View.OnClickListener {
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
            /*if (selectedImageUri == null) {
                layout_root.snackbar("Select an Image First")
                return@setOnClickListener
            }*/

           /* val parcelFileDescriptor =
                contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return@setOnClickListener
            login(
                name,name1,
                email,
                password,
                num
            )
            print(parcelFileDescriptor);*/
        buttonSingIn.setOnClickListener {
            val intent= Intent(this, Login::class.java)
            startActivity(intent)

        }
         /*   m=findViewById(R.id.RegisterbtnSelectimage)
        mImageView=findViewById(R.id.RegisterimageView)
        mSelectImage.setOnClickListener {
            pickImageGallery()
        }*/
        }


    }
}