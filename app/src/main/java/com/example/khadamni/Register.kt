package com.example.khadamni

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Register : AppCompatActivity() {
    private var selectedImageUri: Uri? = null
    var imagePicker: ImageView? = null
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_register)
        var buttonSingIn = findViewById<TextView>(R.id.alreadyHaveAccount);
        var buttonSignUp = findViewById<Button>(R.id.btnRegister)
        var minputUsername = findViewById<EditText>(R.id.inputUsername)
        var minputEmail = findViewById<EditText>(R.id.inputEmail)
        var minputConformPassword = findViewById<EditText>(R.id.inputConformPassword)
        var minputPassword = findViewById<EditText>(R.id.inputPassword)


        super.onCreate(savedInstanceState)
        /* setContentView(R.layout.activity_register)
        imagePicker = findViewById(R.id.profileimage)
        fab = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener(View.OnClickListener {
            ImagePicker.with(this)
                .crop()                  //Crop image(Optional), Check Customization for more option
                .compress(1024)          //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)   //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        })*/
        buttonSignUp.setOnClickListener {
            val name = minputUsername.text.toString().trim()
            val email = minputEmail.text.toString().trim()
            val conformPassword = minputConformPassword.text.toString().trim()
            val password = minputPassword.text.toString().trim()



            if (email.isEmpty()) {
                minputUsername.error = "Email required"
                minputUsername.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                minputPassword.error = "Password required"
                minputPassword.requestFocus()
                return@setOnClickListener
            }
            if (name.isEmpty()) {
                minputUsername.error = "Name required"
                minputUsername.requestFocus()
                return@setOnClickListener
            }
            if (conformPassword.isEmpty()) {
                minputConformPassword.error = "Last name required"
                minputConformPassword.requestFocus()
                return@setOnClickListener
            }
            /*if(num.isEmpty()){
                editTextName.error = "Num required"
                editTextName.requestFocus()
                return@setOnClickListener
            }
            if (selectedImageUri == null) {
                layout_root.snackbar("Select an Image First")
                return@setOnClickListener
            }

            val parcelFileDescriptor =
                contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return@setOnClickListener
            login(
                name,name1,
                email,
                password,
                num
            )
            print(parcelFileDescriptor);
        buttonSingIn.setOnClickListener {
            /* Intent(this, Register::class.java).also {
                 startActivity(it)*/
            val intent= Intent(this,Login::class.java)
            startActivity(intent)

        }*/
            /*mSelectImage=findViewById(R.id.RegisterbtnSelectimage)
        mImageView=findViewById(R.id.RegisterimageView)
        mSelectImage.setOnClickListener {
            pickImageGallery()
        }*/
        }


    }
}