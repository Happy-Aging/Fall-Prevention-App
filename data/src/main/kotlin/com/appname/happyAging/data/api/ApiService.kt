package com.appname.happyAging.data.api

import com.appname.happyAging.data.dto.auth.request.LoginRequest
import com.appname.happyAging.data.dto.auth.request.SignupRequest
import com.appname.happyAging.data.dto.auth.request.SocialLoginRequest
import com.appname.happyAging.data.dto.auth.request.SocialSignupRequest
import com.appname.happyAging.data.dto.auth.response.TokenResponse
import com.appname.happyAging.data.dto.senior.request.CreateSeniorRequest
import com.appname.happyAging.data.dto.senior.request.UpdateSeniorRequest
import com.appname.happyAging.data.dto.senior.response.SeniorDto
import com.appname.happyAging.data.dto.user.request.UpdateUserRequest
import com.appname.happyAging.data.dto.user.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //--------------------- Auth ---------------------//
    @POST("/auth/login")
    @Headers("Auth: false")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @POST("/auth/login/social")
    @Headers("Auth: false")
    suspend fun loginSocial(@Body request: SocialLoginRequest): Response<TokenResponse>

    @POST("/auth/join")
    @Headers("Auth: false")
    suspend fun signup(@Body request: SignupRequest) : Response<TokenResponse>

    @POST("/auth/join/social")
    @Headers("Auth: false")
    suspend fun socialSignup(@Body request: SocialSignupRequest): Response<TokenResponse>

    //--------------------- User ---------------------//
    @GET("/user")
    suspend fun getUser(): Response<UserResponse>

    @PUT("/user")
    suspend fun updateUser(@Body request: UpdateUserRequest) :Response<Unit>

    @DELETE("/user")
    suspend fun deleteUser() : Response<Unit>


    //--------------------- Senior ---------------------//
    @GET("/senior")
    suspend fun getSeniorList(): Response<List<SeniorDto>>

    @POST("/senior")
    suspend fun createSenior(@Body request: CreateSeniorRequest): Response<Long>

    @PUT("/senior/{id}")
    suspend fun updateSenior(@Path("id") id: Long, @Body request: UpdateSeniorRequest) : Response<Unit>

    @DELETE("/senior/{id}")
    suspend fun deleteSenior(@Path("id") id: Long) : Response<Unit>
}


object ApiConstants {
    const val BASE_URL = "http://3.37.58.59:8080"
}