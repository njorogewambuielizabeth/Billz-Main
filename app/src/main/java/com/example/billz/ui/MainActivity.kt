package com.example.billz.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.billz.model.RegisterRequest
import com.example.billz.databinding.ActivityMainBinding
import com.example.billz.utils.Constants
import com.example.billz.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()
    }

    override fun onResume() {
        super.onResume()
       binding.btnSignUp.setOnClickListener{
           validateSignUp()
       }

        binding.btnLogIn2.setOnClickListener {
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
        }


        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
            binding.pbRegister.visibility = View.GONE
        })

        userViewModel.errLiveData.observe(this, Observer {err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })
    }



    fun validateSignUp(){
        val firstName = binding.tieFirstname.text.toString()
        val lastName = binding.tieLastName.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val email = binding.tieEmail.text.toString()
        val password = binding.tiePassword.text.toString()
        val confirmPassword = binding.tieConfirmPassword.text.toString()
        var error = false


        if (firstName.isEmpty()) {
            binding.tieLastName.error = "Username is required"
            error = true

        }
        if (lastName.isEmpty()) {
            binding.tieLastName.error = "Username is required"
            error = true

        }

        if (phoneNumber.isEmpty()) {
            binding.tiePhoneNumber.error = "Phone number is required"
            error = true

        }

        if (email.isEmpty()) {
            binding.tieEmail.error = "Email is required"
            error = true

        }


        if (password.isEmpty()) {
            binding.tiePassword.error = "Password is required"
            error = true

        }
        if (confirmPassword.isEmpty()) {
            binding.tiePassword.error = "Password is required"
            error = true

        }

        if(!error){
            val registerRequest = RegisterRequest(
                firstName = firstName,
                lastname = lastName,
                phoneNumber = phoneNumber,
                email = email,
                password = password,


            )
            userViewModel.registerUser(registerRequest)
        }


   }

    fun redirectUser(){
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getString(Constants.USER_ID, Constants.EMPTY_STRING) ?: Constants.EMPTY_STRING
        if (userId.isNotBlank()){
            startActivity(Intent(this, HomeActivity2::class.java))
            finish()
        }

    }
}



