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
        @Body email: UserReset
    ):Call<UserResetResponse>


    @POST("resetPassword")
    fun resetPassword(
        @Body user: UserRequest,
    ):Call<UserRequest>

    @GET("allusers")
    fun getUsers(): Call<UsersAndMessage>
    @GET("getUserByMail/{id}")
    fun getUserBymail(
        @Body email: String
    ): Call<UserAndMessage>

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

    @GET("getuser/{id}")
    fun getuserbyid( @Path("id") id : String): Call<User>
    @Multipart
    @POST("createuser")
    fun userSignUp(
        @Part urlImg: MultipartBody.Part,
        @PartMap data : LinkedHashMap<String, RequestBody>,
    ) : Call<User>

    @Multipart
    @PUT("updateuser/{id}")
    fun userUpdate(
        @Path("id") id:String,
        @PartMap data : LinkedHashMap<String, RequestBody>,
        @Part urlImg: MultipartBody.Part
    ) : Call<userUpdateResponse>


/*    @POST("/users")
    fun createUser(@Body user: User): Response<CreateUserResponse>*/

    // API Jobs

    @POST("createjob")
    fun addJob(@Body info: RequestBody): Call<Job>



    @GET("alljobs")
    fun AllJobs(): Call<MutableList<Job>>

    @GET("getjob/{id}")
    fun getuserbyemail( @Path("id") id : String): Call<Job>

    @PUT("updatejob/{id}")
    fun updateJob(@Body info: RequestBody,@Path("id") id : String): Call<Job>


    @DELETE("deletejob/{id}")
    fun deleteJob(@Path("id") id : String): Call<Job>


}