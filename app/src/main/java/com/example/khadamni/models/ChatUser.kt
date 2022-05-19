package com.example.khadamni.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ChatUser(
    val firstName: String,
    val username: String
) : Parcelable