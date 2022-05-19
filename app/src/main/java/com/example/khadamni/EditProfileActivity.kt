package com.example.khadamni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.khadamni.models.userUpdateResponse
import com.example.khadamni.services.ApiUser
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null

    private lateinit var fab: FloatingActionButton

    private lateinit var _id: String
    private lateinit var lastName: EditText
    private lateinit var firstName: EditText
    private lateinit var Address: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var job: EditText
    private lateinit var profileimage: ImageView
    lateinit var buttonUpdate: MaterialButton
    lateinit var mSharedPref: SharedPreferences

    var imagePicker: ImageView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.hide()
        mSharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        profileimage = findViewById(R.id.profileimage)
        val picStr: String = mSharedPref.getString("URLIMG", "my photos").toString()
        println(picStr)
        if(picStr != "my photos"){
            Picasso.get().load(mSharedPref.getString("URLIMG","")).into(profileimage)
        }





        val address: String = mSharedPref.getString("ADDRESS", "address").toString()
        Address = findViewById(R.id.updateAddress)
        Address.setText(address)

        val first: String = mSharedPref.getString("PRENOM", "firstname").toString()
        firstName = findViewById(R.id.firstName)
        firstName.setText(first)

        val last: String = mSharedPref.getString("NOM", "lastname").toString()
        lastName = findViewById(R.id.lastName)
        lastName.setText(last)

        val phone: String = mSharedPref.getString("PHONE", "telephone number").toString()
        phoneNumber = findViewById(R.id.Number)
        phoneNumber.setText(phone)

        val numberNational: String = mSharedPref.getString("JOB", "job").toString()
        job = findViewById(R.id.updateJob)
        job.setText(numberNational)

        buttonUpdate = findViewById(R.id.buttonUpdate)
        imagePicker = findViewById(R.id.profileimage)
        fab = findViewById(R.id.fab)

        fab.setOnClickListener(View.OnClickListener {
            ImagePicker.with(this)
                .crop()                 //Crop image(Optional), Check Customization for more option
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )  //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        })


        buttonUpdate.setOnClickListener {

            val myAddress = Address.text.toString().trim()
            val myFirstName = firstName.text.toString().trim()
            val myLastName = lastName.text.toString().trim()
            val myPhoneNumber = phoneNumber.text.toString().trim()
            val myJob = job.text.toString().trim()

            if (myAddress.isEmpty()) {
                Address.error = "Address required"
                Address.requestFocus()
                return@setOnClickListener
            }

            if (myFirstName.isEmpty()) {
                firstName.error = "Name required"
                firstName.requestFocus()
                return@setOnClickListener
            }
            if (myLastName.isEmpty()) {
                lastName.error = "Last name required"
                lastName.requestFocus()
                return@setOnClickListener
            }
            if (myPhoneNumber.isEmpty()) {
                phoneNumber.error = "Num required"
                phoneNumber.requestFocus()
                return@setOnClickListener
            }

            if (myJob.isEmpty()) {
                phoneNumber.error = "Job required"
                phoneNumber.requestFocus()
                return@setOnClickListener
            }
            /* if (selectedImageUri == null) {
                 applicationContext.snackbar("Select an Image First")
                 return@setOnClickListener
             }*/


            val parcelFileDescriptor =
                contentResolver.openFileDescriptor(selectedImageUri!!, "r", null)
                    ?: return@setOnClickListener
            _id = mSharedPref.getString("ID", "my id").toString()

            update(
                myAddress,
                myFirstName,
                myLastName,
                myPhoneNumber,
                myJob,
                _id
            )
            print(parcelFileDescriptor);

        }




    }


    private fun update(myAddress: String, myFirstName: String, myLastName: String, myPhoneNumber: String, myJob: String,id: String){

        if(selectedImageUri == null){
            println("image null")

            return
        }

        val id1 = _id;
        //id1 = mSharedPref.getString("id", "user.id").toString();

        println(id1);
        val stream = contentResolver.openInputStream(selectedImageUri!!)
        val request =
            stream?.let { RequestBody.create("image/png".toMediaTypeOrNull(), it.readBytes()) } // read all bytes using kotlin extension
        val photos = request?.let {
            MultipartBody.Part.createFormData(
                "urlImg",
                "file.png",
                it
            )
        }


        Log.d("MyActivity", "on finish upload file")


        val apiInterface = ApiUser.create()
        val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()

        data["nom"] = RequestBody.create(MultipartBody.FORM, myLastName)
        data["prenom"] = RequestBody.create(MultipartBody.FORM, myFirstName)
        data["address"] = RequestBody.create(MultipartBody.FORM, myAddress)
        data["phone"] = RequestBody.create(MultipartBody.FORM, myPhoneNumber)
        data["job"] = RequestBody.create(MultipartBody.FORM, myJob)

        if (photos != null) {
            println("Ahla ena image : $photos")
            apiInterface.userUpdate(_id,data,photos).enqueue(object:
                Callback<userUpdateResponse> {
                override fun onResponse(
                    call: Call<userUpdateResponse>,
                    response: Response<userUpdateResponse>
                ) {
                    if(response.isSuccessful){
                        Log.i("onResponse goooood", response.body().toString())



                        mSharedPref.edit().apply{
                            putString("ID", response.body()?.user?._id.toString())
                            putString("NOM", response.body()?.user?.nom.toString())
                            putString("PRENOM", response.body()?.user?.prenom.toString())
                            putString("EMAIL", response.body()?.user?.email)
                            putString("PASSWORD", response.body()?.user?.password)
                            putString("ADDRESS", response.body()?.user?.address.toString())
                            putString("JOB", response.body()?.user?.job.toString())
                            putString("PHONE", response.body()?.user?.phone.toString())
                            putString("URLIMG", response.body()?.user?.urlImg.toString())

                            println(response.body()?.user?.password.toString())
                            //putBoolean("session", true)
                        }.apply()

                        finish()
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {
                        Log.i("OnResponse not good", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<userUpdateResponse>, t: Throwable) {

                    println("Failed to do request")
                }

            })
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE){
            selectedImageUri = data?.data
            imagePicker?.setImageURI(selectedImageUri)

        }
    }
}