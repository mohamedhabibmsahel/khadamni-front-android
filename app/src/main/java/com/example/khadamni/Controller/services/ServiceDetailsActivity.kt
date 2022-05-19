package com.example.khadamni.Controller.services

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quickaccesswallet.WalletCard
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.khadamni.R
import com.example.khadamni.models.User
import com.example.khadamni.services.ApiUser
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.material.imageview.ShapeableImageView
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceDetailsActivity : AppCompatActivity(), PaymentResultListener {

    lateinit var btPay: Button
    lateinit var UserName : TextView
    lateinit var UserLocation : TextView

    lateinit var desription : TextView
    lateinit var usernamePic : ShapeableImageView
    lateinit var pricetext : TextView


    lateinit var to : String
    lateinit var description : String
    lateinit var Price : String
    lateinit var _id : String
    lateinit var idfrom : String
    lateinit var mSharedPref : SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

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

        usernamePic = findViewById(R.id.usernamePic)
        getbyid(to)

        btPay = findViewById<Button>(R.id.paymentBtn)
        mSharedPref = getSharedPreferences("SHARED_PREF", AppCompatActivity.MODE_PRIVATE);
        if(idfrom!=mSharedPref.getString("ID","")){
        btPay.setVisibility(View.GONE);
        }else{
            btPay.setVisibility(View.VISIBLE);
        }
        desription.text = description
        pricetext.text = Price
        val sAmount = Price

        val amout = Math.round(sAmount.toFloat() * 100)

        btPay.setOnClickListener(View.OnClickListener {
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_vJiRBkgAEUE5Jn")
            val `object` = JSONObject()
            try {
                `object`.put("name", "Khademni")
                `object`.put("description", "Payment")
                `object`.put("currency", "USD")
                `object`.put("amount", amout)
                `object`.put("prefill.contact", "9876543210")
                `object`.put("prefill.email", "Gouidersaif@gmail.com")
                checkout.open(this@ServiceDetailsActivity, `object`)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
    }

    override fun onPaymentSuccess(p0: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Payment id")
        builder.setMessage(p0)
        builder.show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(applicationContext, p1, Toast.LENGTH_SHORT).show()
    }

    private fun getbyid(id:String ) {

        val apiInterface = ApiUser.create()


        apiInterface.getuserbyid(id).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                val user = response.body()

                if (user != null){
                    UserName.text ="Name : " + user.nom
                    UserLocation.text = "Address : " + user.address

                    Picasso.get().load(user.urlImg).into(usernamePic)
                }else{

                }


            }

            override fun onFailure(call: Call<User>, t: Throwable) {




            }

        })


    }
}