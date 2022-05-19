package com.example.khadamni.models

import java.util.*

data class User(
    var _id: String? = null,
    var nom: String? = null,
    var prenom: String? = null,
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
data class UserAndToken(
    var user: User? = null,
    var token: String? = null

)
data class UsersAndMessage(
    var users: MutableList<User>?= null,
    var message: String? = null

)
data class UserAndMessage(
    var user: User?= null,
    var reponse: String? = null

)
data class UserRequest(
    var token: String?=null,
    var msg: String?=null,
    var succes: String?=null,
    var response: String?=null,
    var email: String?=null,
    var password: String?=null,
)
data class UserResetResponse(
    var token: String?=null,
    var msg: String?=null,
    var succes: String?=null,
    var response: String?=null,
    var user : User? = null,
)
data class UserReset(
    var email: String?=null,

    )
data class userUpdateResponse(
    var user : User? = null,
)
