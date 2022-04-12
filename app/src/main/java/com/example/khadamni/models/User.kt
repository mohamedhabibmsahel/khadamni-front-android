package com.example.khadamni.models

import java.util.*

data class User(
    var _id: String? = null,
    var lastName: String? = null,
    var firstName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var job: String? = null,
    var urlImg: String? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
    var __v: Int? = null,
    var token: String? = null
)
data class userSignUpResponse (
    val user : User? = null,
    val token : String? = null,
    val reponse : String? = null
)
