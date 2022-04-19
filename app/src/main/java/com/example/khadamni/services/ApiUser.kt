package com.example.khadamni.services
import com.example.khadamni.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiUser {
    companion object {
        fun create(): ApiUser {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:3000")
                .build()
            return retrofit.create(ApiUser::class.java)

        }
    }

    /* if needed query params @Query("key") key: String*/
    @POST("forgotPassword")
    fun sendResetCode(
        @Body emailAddress: UserReset
    ):Call<UserResetResponse>


    @POST("resetPassword/{emailAddress}/{token}")
    fun resetPassword(
        @Path("emailAddress") emailAddress: String,
        @Path("token") token:String,
        @Body password: String,
    ):Call<UserResetResponse>

    @GET("allusers")
    fun getUsers(): Response<List<User>>

    @POST("login")
    fun userLogin(
        @Body user: User,
        ): Call<UserAndToken>
    @Multipart
    @POST("user")
    fun userSignUp(
        @PartMap data : LinkedHashMap<String, RequestBody>,
        @Part profilePicture: MultipartBody.Part
    ) : Call<userSignUpResponse>



/*    @POST("/users")
    fun createUser(@Body user: User): Response<CreateUserResponse>*/
}