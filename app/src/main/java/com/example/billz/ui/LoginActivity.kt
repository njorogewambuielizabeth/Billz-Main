package com.example.billz.ui
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.utils.Constants
import com.example.billz.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onResume()
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignUp2.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, HomeActivity2::class.java)
            startActivity(intent)
            validateLogin()
        }

        initObserver()

    }

     fun validateLogin(){
        val email = binding.tieEmail2.text.toString()
        val password = binding.tiePasswordtwo.text.toString()
        var error = false


        if (email.isBlank()) {
            binding.tieEmail2.error = "Email is required"
            error = true

        }


        if (password.isBlank()) {
            binding.tiePasswordtwo.error = "Password is required"
            error = true

        }

        if (!error){
            userViewModel.loginUser(LoginRequest(email, password))
        }


    }
    fun initObserver(){
        userViewModel.loginLiveData.observe(this){loginResponse->
            persistLogin(loginResponse)
            Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()

        }

        userViewModel.errLiveData.observe(this){error->
            binding.pbRegister2.visibility = View.GONE
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity2::class.java))
        }
    }

    fun persistLogin(loginResponse: LoginResponse){
        val sharePrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharePrefs.edit()
        editor.putString(Constants.USER_ID,loginResponse.userId)
        editor.putString(Constants.ACCESS_TOKEN, loginResponse.accessToken)
        editor.apply()
    }
}








