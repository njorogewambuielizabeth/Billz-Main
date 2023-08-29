package com.example.billz.api

import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.model.RegisterRequest
import com.example.billz.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @POST("/users/login")
    suspend fun loginUser(@Body registerRequest: LoginRequest): Response<LoginResponse>
}




