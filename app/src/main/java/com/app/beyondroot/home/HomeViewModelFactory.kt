package com.app.beyondroot.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.beyondroot.databinding.ActivityHomeBinding

class HomeViewModelFactory(var context: HomeActivity, var activityHomeBinding: ActivityHomeBinding
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return HomeViewModel(context,activityHomeBinding) as T
    }

}