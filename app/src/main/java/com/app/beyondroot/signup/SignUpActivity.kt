package com.app.beyondroot.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.beyondroot.R
import com.app.beyondroot.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity(){
    lateinit var activitySignupBinding:ActivitySignupBinding
    lateinit var signUpViewModel:SignUpViewModel
    lateinit var signUpViewModelFactory:SignUpViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        signUpViewModelFactory=SignUpViewModelFactory(this@SignUpActivity,activitySignupBinding)
        signUpViewModel=ViewModelProvider(this,signUpViewModelFactory).get(SignUpViewModel::class.java)
        activitySignupBinding.signupviewmodel=signUpViewModel
    }
}