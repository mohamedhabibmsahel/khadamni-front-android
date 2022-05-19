package com.example.khadamni

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.khadamni.databinding.FragmentLoginBinding
import com.example.khadamni.models.ChatUser
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var mSharedPref : SharedPreferences;
    lateinit var nomm : String;
    lateinit var prenom : String;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        mSharedPref = this.requireActivity()?.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);


        nomm =  mSharedPref.getString("NOM","")!!
        prenom =  mSharedPref.getString("JOB","")!!

            authenticateTheUser()


        return binding.root
    }

    private fun authenticateTheUser() {


            val chatUser = ChatUser(nomm, prenom)
            val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(chatUser)
            findNavController().navigate(action)

    }

    private fun validateInput(inputText: String, textInputLayout: TextInputLayout): Boolean {
        return if (inputText.length <= 3) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "* Minimum 4 Characters Allowed"
            false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}