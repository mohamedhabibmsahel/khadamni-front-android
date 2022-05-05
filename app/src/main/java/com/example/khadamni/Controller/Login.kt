package com.example.khadamni.Controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.khadamni.HomeActivity
import com.example.khadamni.R
import com.example.khadamni.Register
import com.example.khadamni.models.*
import com.example.khadamni.services.ApiUser
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.marcoscg.dialogsheet.DialogSheet
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.util.*


class Login : AppCompatActivity() {
    //var isRemembred = false
    lateinit var customEmail: String
    lateinit var mycode: String
    lateinit var username: TextView
    lateinit var password: TextView
    lateinit var button: Button
    lateinit var GoogleButton: Button
    lateinit var FacebookButton: Button
    lateinit var buttonSingUp: TextView
    lateinit var forgotpass: TextView
    lateinit var mSharedPref: SharedPreferences
    lateinit var gso : GoogleSignInOptions
    lateinit var gsc :GoogleSignInClient
    lateinit var callbackManager : CallbackManager
    var isLoggedIn : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso)
        GoogleButton = findViewById(R.id.btnGoogle)
        FacebookButton = findViewById(R.id.btnFacebook)
        forgotpass = findViewById(R.id.forgotPassword)
        username= findViewById(R.id.inputEmail);
        password =findViewById(R.id.inputPassword);
        button = findViewById(R.id.btnlogin);
        buttonSingUp= findViewById(R.id.textViewSignUp);
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
        GoogleButton.setOnClickListener {
            signIn()
            val  acct = GoogleSignIn.getLastSignedInAccount(this)
            if(acct!=null){
                println("Nom"+acct.displayName)
                println("Email"+acct.email)
                println("Imaage"+acct.photoUrl)
            }
        }

        FacebookButton.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val graphRequest = GraphRequest.newMeRequest (loginResult?.accessToken){`object`, response->
                        getFacebookData(`object`)}
                    val parameters = Bundle()
                    parameters.putString("fields","id,email,birthday,friends, gender, name,picture.type(large)")
                    graphRequest.parameters = parameters
                    graphRequest.executeAsync()
                }


                override fun onCancel() {
                    println("erreur cancel")
                }

                override fun onError(exception: FacebookException) {
                    println("erreur erreur")
                }
            })
        button.setOnClickListener {
            var myUser = User()

            myUser.email = username.text.toString()
            myUser.password = password.text.toString()
            val apiUser = ApiUser.create().userLogin(myUser)
            apiUser.enqueue(object : Callback<UserAndToken> {
                override fun onResponse(
                    call: Call<UserAndToken>,
                    response: Response<UserAndToken>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "good", Toast.LENGTH_LONG).show()
                        Log.i("Login User", response.body()!!.toString())
                        mSharedPref.edit().apply {
                            putString("ID", response.body()?.user?._id.toString())
                            putString("NOM", response.body()?.user?.nom.toString())
                            putString("PRENOM", response.body()?.user?.prenom.toString())
                            putString(
                                "EMAIL", username.text.toString()
                            )
                            putString(
                                "PASSWORD", password.text.toString()
                            )
                            putString(
                                "TOKEN",
                                response.body()?.token.toString()
                            )
                            putString("ADDRESS", response.body()?.user?.address.toString())
                            putString("JOB", response.body()?.user?.job.toString())
                            putString("PHONE", response.body()?.user?.phone.toString())
                            putString("URLIMG", response.body()?.user?.urlImg.toString())
                            putBoolean("ISREMEMBRED",true);

                        }.apply()
                        finish()

                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        println(
                            "My shaaaaaaaaaaaaaaaaaared prefs email ! " + mSharedPref.getString(
                                "EMAIL",
                                "hey"
                            )
                        );
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

                override fun onFailure(call: Call<UserAndToken>, t: Throwable) {
                    Toast.makeText(applicationContext, "SERVER ERROR", Toast.LENGTH_LONG).show()
                }
            })
        }




        buttonSingUp.setOnClickListener {
            /* Intent(this, Register::class.java).also {
                 startActivity(it)*/
            val intent= Intent(this, Register::class.java)
            startActivity(intent)

        }

        //Confirm Password Dialog


        val dialogSheetPassword = DialogSheet(this@Login, true)
        dialogSheetPassword.setView(R.layout.reset_password_dialog_view)
        val dialogSheetPasswordfactory = layoutInflater
        val passwordView: View =
            dialogSheetPasswordfactory.inflate(R.layout.reset_password_dialog_view, null)

        // you can also use DialogSheet2 if you want the new style
        //.setNewDialogStyle() // You can also set new style by this method, but put it on the first line
        dialogSheetPassword.setTitle("Change Your Password")
            .setMessage("You should enter password and confirm your password :")
            .setSingleLineTitle(true)
            .setColoredNavigationBar(true)
        //.setButtonsColorRes(R.color.colorAccent) // You can use dialogSheetAccent style attribute instead

        /* .setPositiveButton(android.R.string.ok) {
             println("elcode+----------------"+code1.text.toString())
             Toast.makeText(this@LoginPro, "code have been sent", Toast.LENGTH_SHORT).show()
         }
         .setNegativeButton(android.R.string.cancel)
         .setNeutralButton("Neutral")
     */
        dialogSheetPassword.setIconResource(R.drawable.ic_logo)

        val inflatedViewPassword = dialogSheetPassword.inflatedView

        val newinflatedView = dialogSheetPassword.inflatedView
        val sendPassword = inflatedViewPassword?.findViewById<Button>(R.id.sendPassword)
        val customPassword = inflatedViewPassword?.findViewById<EditText>(R.id.custom_password)
        val customConfirmPassword =
            inflatedViewPassword?.findViewById<EditText>(R.id.custom_confirm_password)

        sendPassword?.setOnClickListener {

            val verification = UserRequest()
            verification.token = mycode
            verification.email=customEmail
            verification.password = customPassword!!.text.toString()
            /*verification.user?.emailAddress = customEmail*/
            println("email !! : " + customEmail)
            println("verification !! : " + verification)
            println("code !! : " + mycode)
            println("pass !! : " + customPassword!!.text.toString())
            val apiuser = ApiUser.create().resetPassword(
                verification
            )
            apiuser.enqueue(object : Callback<UserRequest> {
                override fun onResponse(
                    call: Call<UserRequest>,
                    response: Response<UserRequest>
                ) {
                    if (response.isSuccessful) {


                        MotionToast.darkColorToast(
                            this@Login,
                            "Good ",
                            "Success reset Password",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_TOP,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(
                                this@Login,
                                www.sanju.motiontoast.R.font.helvetica_regular
                            )
                        )

                        dialogSheetPassword.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "Your Password have been changed ",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("PASSSSS_API_RESPONSE", response.toString())

                    } else {

                        Toast.makeText(applicationContext, "Incorrect email ", Toast.LENGTH_LONG)
                            .show()
                        Log.i("PASSSSS_API_RESPONSE", response.toString())
                    }
                }

                override fun onFailure(call: Call<UserRequest>, t: Throwable) {
                    Toast.makeText(applicationContext, "erreur server", Toast.LENGTH_LONG).show()
                }

            })


        }


        //Custom dialog sheet

        val dialogSheet = DialogSheet(this@Login, true)
        dialogSheet.setView(R.layout.custom_dialog_view)
        val factory = layoutInflater
        val view: View = factory.inflate(R.layout.custom_dialog_view, null)

        // you can also use DialogSheet2 if you want the new style
        //.setNewDialogStyle() // You can also set new style by this method, but put it on the first line
        dialogSheet.setTitle("Reset Password")
            .setMessage("Verification code will be sent to the mail")
            .setSingleLineTitle(true)
            .setColoredNavigationBar(true)
        //.setButtonsColorRes(R.color.colorAccent) // You can use dialogSheetAccent style attribute instead

        /* .setPositiveButton(android.R.string.ok) {
             println("elcode+----------------"+code1.text.toString())
             Toast.makeText(this@LoginPro, "code have been sent", Toast.LENGTH_SHORT).show()
         }
         .setNegativeButton(android.R.string.cancel)
         .setNeutralButton("Neutral")
     */
        dialogSheet.setIconResource(R.drawable.ic_logo)

        val inflatedView = dialogSheet.inflatedView
        val sendcode = inflatedView?.findViewById<Button>(R.id.sendCode)
        val customEditTextemail = inflatedView?.findViewById<EditText>(R.id.customEditTextemail)

        val code1 = inflatedView?.findViewById<EditText>(R.id.code1)
        val code2 = inflatedView?.findViewById<EditText>(R.id.code2)
        val code3 = inflatedView?.findViewById<EditText>(R.id.code3)
        val code4 = inflatedView?.findViewById<EditText>(R.id.code4)



        code1?.isEnabled = false
        code2?.isEnabled = false
        code3?.isEnabled = false
        code4?.isEnabled = false





        sendcode?.setOnClickListener {
            var userReset = UserReset()
            userReset.email = customEditTextemail?.text.toString()
            customEmail = customEditTextemail?.text.toString()
            val apiuser = ApiUser.create().sendResetCode(userReset)

            apiuser.enqueue(object : Callback<UserResetResponse> {
                override fun onResponse(
                    call: Call<UserResetResponse>,
                    response: Response<UserResetResponse>
                ) {
                    println("++++++++++++++response" + response.body()?.msg.toString())
                    if (response.isSuccessful) {

                        if (response.body()?.msg.toString() == "false") {
                            MotionToast.darkColorToast(
                                this@Login,
                                "Failed ",
                                "Wrong email !",
                                MotionToastStyle.WARNING,
                                MotionToast.GRAVITY_TOP,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(
                                    this@Login,
                                    www.sanju.motiontoast.R.font.helvetica_regular
                                )
                            )
                            code1?.isEnabled = false
                            code2?.isEnabled = false
                            code3?.isEnabled = false
                            code4?.isEnabled = false
                        }

                        if (response.body()?.succes.toString() == "true") {


                            MotionToast.darkColorToast(
                                this@Login,
                                "Success ",
                                "code sent!",
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_TOP,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(
                                    this@Login,
                                    www.sanju.motiontoast.R.font.helvetica_regular
                                )
                            )
                            code1?.isEnabled = true
                            code2?.isEnabled = true
                            code3?.isEnabled = true
                            code4?.isEnabled = true

                            code1?.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    count: Int,
                                    after: Int
                                ) {

                                }

                                override fun onTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                    if (s.length == 1) {
                                        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                                        code2?.requestFocus()
                                    }
                                    code2?.addTextChangedListener(object : TextWatcher {
                                        override fun beforeTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            count: Int,
                                            after: Int
                                        ) {

                                        }

                                        override fun onTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            before: Int,
                                            count: Int
                                        ) {
                                            if (s.length == 1) {
                                                println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                                                code3?.requestFocus()
                                            }
                                        }

                                        override fun afterTextChanged(s: Editable) {


                                        }
                                    })
                                    code3?.addTextChangedListener(object : TextWatcher {
                                        override fun beforeTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            count: Int,
                                            after: Int
                                        ) {

                                        }

                                        override fun onTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            before: Int,
                                            count: Int
                                        ) {
                                            if (s.length == 1) {
                                                println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                                                code4?.requestFocus()
                                            }
                                        }

                                        override fun afterTextChanged(s: Editable) {


                                        }
                                    })
                                    code4?.addTextChangedListener(object : TextWatcher {
                                        override fun beforeTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            count: Int,
                                            after: Int
                                        ) {

                                        }

                                        override fun onTextChanged(
                                            s: CharSequence,
                                            start: Int,
                                            before: Int,
                                            count: Int
                                        ) {
                                            if (s.length == 1) {
                                                println("Ena code -------------")
                                                mycode =
                                                    code1?.text.toString() + code2?.text.toString() + code3?.text.toString() + code4?.text.toString()
                                                println(mycode)
                                                println("Ena token ----------------------------")
                                                println(response.body()?.token.toString())
                                                if (response.body()?.token.toString() == mycode) {
                                                    dialogSheet.dismiss()
                                                    dialogSheetPassword.show()
                                                } else {
                                                    Toast.makeText(
                                                        applicationContext,
                                                        "Invalid Code",
                                                        Toast.LENGTH_LONG
                                                    )
                                                        .show()
                                                }


                                            }
                                        }

                                        override fun afterTextChanged(s: Editable) {


                                        }
                                    })
                                }

                                override fun afterTextChanged(s: Editable) {


                                }
                            })

                        }


                        //Toast.makeText(applicationContext, "Code Sent Successfully", Toast.LENGTH_LONG).show()


                    } else {

                        Toast.makeText(applicationContext, "Failed to Login", Toast.LENGTH_LONG)
                            .show()

                    }
                }

                override fun onFailure(call: Call<UserResetResponse>, t: Throwable) {

                    MotionToast.darkColorToast(
                        this@Login,
                        "Failed ",
                        "Server problem",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_TOP,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(
                            this@Login,
                            www.sanju.motiontoast.R.font.helvetica_regular
                        )
                    )
                }


            })




            println("elcode222+----------------" + customEditTextemail?.text.toString())
        }
        forgotpass.setOnClickListener { dialogSheet.show() }
    }
    //SignIn method
    fun signIn(){
        var signInIntent : Intent  = gsc.getSignInIntent()
        startActivityForResult(signInIntent,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000){
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account = completedTask.getResult(ApiException::class.java)
        }catch (e: ApiException){
            Toast.makeText(applicationContext, "Google ",Toast.LENGTH_SHORT).show()
        }

    }
    fun getFacebookData(obj: JSONObject?){
        println(obj?.getString("name"))
        println(obj?.getString("email"))
        val profilePic = obj!!.getJSONObject("picture").getJSONObject("data").getString("url");
        println(profilePic.toString())
    }
}
