package com.example.khadamni

import android.content.SharedPreferences
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.khadamni.Controller.Login
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.squareup.picasso.Picasso


class ProfilFragment : Fragment(R.layout.fragment_profil) {
    lateinit var mLogOut: ImageView
    lateinit var mSharedPref : SharedPreferences;
    lateinit var gso : GoogleSignInOptions
    lateinit var gsc : GoogleSignInClient
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profil, container, false)
        mSharedPref = requireActivity().getSharedPreferences("SHARED_PREF", AppCompatActivity.MODE_PRIVATE);
        val email: TextView =rootView.findViewById(R.id.idEmail);
        email.setText(mSharedPref.getString("EMAIL",""));
        val nom: TextView =rootView.findViewById(R.id.idfullname);
        nom.setText(mSharedPref.getString("NOM","No name")+mSharedPref.getString("PRENOM","No prenom"));
        val address: TextView =rootView.findViewById(R.id.idAddress);
        address.setText(mSharedPref.getString("ADDRESS",""));
        val job: TextView =rootView.findViewById(R.id.idjob);
        job.setText(mSharedPref.getString("JOB","batal"));
        val phone: TextView =rootView.findViewById(R.id.idphone);
        phone.setText("+216 "+mSharedPref.getString("PHONE","000000"));
        println(mSharedPref.getString("URLIMG",""))
        val urlimage: ImageView =rootView.findViewById(R.id.idUrlImg);
        Picasso.get().load(mSharedPref.getString("URLIMG","")).into(urlimage)
        mLogOut = rootView.findViewById<ImageView?>(R.id.idLogOut)
        mLogOut.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("LogOut")
                .setMessage("Are you sure you want to Logout ?")
                .setPositiveButton("Yes"){ dialogInterface, which ->

                    mSharedPref.edit().clear().apply()
                    signOut()
                    val intent= Intent(activity,Login::class.java)
                    startActivity(intent)


                }.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }.create().show()
        }
        return rootView
    }
    fun signOut() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(requireActivity(),gso)
        gsc.signOut().addOnCompleteListener(requireActivity(),OnCompleteListener<Void?> {
        })
    }

}