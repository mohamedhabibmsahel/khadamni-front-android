package com.example.khadamni.Controller.services

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quickaccesswallet.WalletCard
import com.example.khadamni.R
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet

class ServiceDetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)
    }
}