package com.app.beyondroot.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.beyondroot.Constants
import com.app.beyondroot.R
import com.app.beyondroot.databinding.ActivityHomeBinding
import com.app.beyondroot.login.LoginActivity

class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding:ActivityHomeBinding
    lateinit var homeViewModel:HomeViewModel
    lateinit var homeViewModelFactory:HomeViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        homeViewModelFactory= HomeViewModelFactory(this@HomeActivity,activityHomeBinding)
        homeViewModel=ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)
        activityHomeBinding.homeviewmodel=homeViewModel
    }

    override fun onBackPressed() {
        var alertDialog= AlertDialog.Builder(this@HomeActivity)
        alertDialog.setTitle("Exit")
        alertDialog.setMessage("Are you sure you want to exit the app?")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
               finishAndRemoveTask()
            }
        })
        alertDialog.setNegativeButton("No",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0?.dismiss()
            }
        })
        alertDialog.show()
    }
}