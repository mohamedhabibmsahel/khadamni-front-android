package com.example.khadamni.services

import com.example.khadamni.models.User
import com.example.khadamni.models.userSignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiUser {

    /* if needed query params @Query("key") key: String*/

    @GET("/allusers")
    fun getUsers(): Response<List<User>>

    @POST("/login")
    fun userLogin(
        @Body user: User,
        ): Call<User>
    @Multipart
    @POST("user")
    fun userSignUp(
        @PartMap data : LinkedHashMap<String, RequestBody>,
        @Part profilePicture: MultipartBody.Part
    ) : Call<userSignUpResponse>



/*    @POST("/users")
    fun createUser(@Body user: User): Response<CreateUserResponse>*/
}