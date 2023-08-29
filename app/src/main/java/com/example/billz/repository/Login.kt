package com.example.billz.repository

import com.example.billz.api.ApiClient
import com.example.billz.api.ApiInterface
import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.model.RegisterRequest
import com.example.billz.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Login {
    suspend fun login(loginRequest: LoginRequest): Any {
        return withContext(Dispatchers.IO) {
            val apiClient  = ApiClient.buildApiClient(ApiInterface::class.java)
            apiClient.loginUser(loginRequest)
        }

    }

    }

//    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

//    suspend fun loginUser(loginRequest: LoginRequest): Response<RegisterResponse> {
//        return withContext(Dispatchers.IO) {
//            apiClient.loginUser(loginRequest)
//        }
//    }

//    companion object {
//        suspend fun loginUser(loginRequest: LoginRequest) {
//            return withContext(Dispatchers.IO) {
//                val apiClient  = ApiClient.buildApiClient(ApiInterface::class.java)
//                apiClient.loginUser(loginRequest)
//            }
//
//        }
//    }

