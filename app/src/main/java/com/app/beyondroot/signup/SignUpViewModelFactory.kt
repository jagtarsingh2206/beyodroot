package com.app.beyondroot.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.beyondroot.databinding.ActivitySignupBinding

class SignUpViewModelFactory(
    var context: SignUpActivity,
    var activitySignupBinding: ActivitySignupBinding
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     return SignUpViewModel(context,activitySignupBinding) as T
    }

}