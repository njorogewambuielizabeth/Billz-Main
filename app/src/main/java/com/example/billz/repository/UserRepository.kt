package com.example.billz.repository

import com.example.billz.model.RegisterRequest
import com.example.billz.api.ApiClient
import com.example.billz.api.ApiInterface
import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)


    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {

        return  withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }


    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }



}