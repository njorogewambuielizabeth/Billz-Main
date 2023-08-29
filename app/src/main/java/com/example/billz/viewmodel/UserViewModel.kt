package com.example.billz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.model.RegisterRequest
import com.example.billz.model.RegisterResponse
import com.example.billz.repository.Login
import com.example.billz.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepo = UserRepository()
    val regLiveData = MutableLiveData<RegisterResponse>()
    val errLiveData = MutableLiveData<String>()
    val loginLiveData = MutableLiveData<LoginResponse>()


    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.register(registerRequest)
            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepo.login(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }


}


