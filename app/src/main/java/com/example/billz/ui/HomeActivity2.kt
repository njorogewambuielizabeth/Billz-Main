package com.example.billz.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.billz.R
import com.example.billz.databinding.ActivityHome2Binding
import com.example.billz.utils.Constants
import java.net.Inet4Address

class HomeActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityHome2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNav()
    }
    fun setUpBottomNav(){
       binding.bnvHome.setOnItemSelectedListener { menuItem->
           when(menuItem.itemId){
               R.id.summary ->{
              supportFragmentManager
                  .beginTransaction()
                  .replace(R.id.fcvHome, SummaryFragment())
                  .commit()
              true

               }
               R.id.upcoming ->{
                   supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.fcvHome, UpcomingBillsFragment())
                       .commit()
                   true
               }
               R.id.paid ->{
                   supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.fcvHome, PaidBillsFragment())
                       .commit()
                   true
               }
               R.id.settings ->{
                   supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.fcvHome, SettingsFragment())
                       .commit()
                   true
               }
              else ->{
                  false
              }
           }
       }
    }
}