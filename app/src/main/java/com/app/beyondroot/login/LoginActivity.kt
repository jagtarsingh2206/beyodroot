package com.app.beyondroot.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.beyondroot.R
import com.app.beyondroot.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var activityLoginBinding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var loginViewModelFactory: LoginViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginViewModelFactory= LoginViewModelFactory(this@LoginActivity)
        loginViewModel=ViewModelProvider(this,loginViewModelFactory).get(LoginViewModel::class.java)
        activityLoginBinding.loginviewmodel=loginViewModel
    }

    override fun onBackPressed() {
            var alertDialog= AlertDialog.Builder(this@LoginActivity)
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