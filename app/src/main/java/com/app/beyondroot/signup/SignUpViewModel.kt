package com.app.beyondroot.signup

import android.app.Activity
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.beyondroot.database.BeyodrootDatabase
import com.app.beyondroot.database.dao.UsersDao
import com.app.beyondroot.database.enitity.UserDetails
import com.app.beyondroot.databinding.ActivitySignupBinding
import java.util.concurrent.Executors

class SignUpViewModel(
    var context: SignUpActivity,
    var activitySignupBinding: ActivitySignupBinding
) : ViewModel() {
    var userdetails = UserDetails(0,"","","","","","")
    var beyodrootDatabase:BeyodrootDatabase?=null
    var usersDao:UsersDao?=null
    init {
        beyodrootDatabase= BeyodrootDatabase.getInstance(context)
        usersDao=beyodrootDatabase!!.userDao
    }

    fun submitdetails(view: View) {
        if (userdetails?.name != null && userdetails.name.trim().length > 0) {
            if (userdetails.email != null && userdetails.email.trim().length > 0) {
                if (isEmailValid(userdetails.email)) {
                    if (userdetails.phonenumber != null && userdetails.phonenumber.trim().length > 0) {
                      if(userdetails.phonenumber.trim().length>=10){
                          if(userdetails.password.trim().length>=6){
                             if(userdetails.city.trim().length>0){
                                 checkExistingValues()
                             }else{
                                 Toast.makeText(context, "City required", Toast.LENGTH_LONG).show()
                             }
                          }else{
                              Toast.makeText(context, "Password must contain atleat 6 characters", Toast.LENGTH_LONG).show()
                          }
                      }else{
                          Toast.makeText(context, "Invalid phone number", Toast.LENGTH_LONG).show()
                      }
                    }else{
                        Toast.makeText(context, "Phone number required", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Email required", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Name required", Toast.LENGTH_LONG).show()
        }

    }

    private fun checkExistingValues() {
       var isEmailExist=usersDao!!.getEmailUsers(userdetails!!.email)
       var isPhoneExist=usersDao!!.getPhoneUsers(userdetails!!.phonenumber)
       var isPasswordExist=usersDao!!.getPassword(userdetails!!.password)
        Log.d("TAG_LOGS","="+isEmailExist)
        Log.d("TAG_LOGS","="+isPhoneExist)
        Log.d("TAG_LOGS","="+isPasswordExist)
        if(isEmailExist>0){
            Toast.makeText(context, "Email is already registered", Toast.LENGTH_LONG).show()
        }else if(isPhoneExist>0){
            Toast.makeText(context, "Phonenumber is already registered", Toast.LENGTH_LONG).show()
        }else if(isPasswordExist>0){
            Toast.makeText(context, "Password is already used by other user", Toast.LENGTH_LONG).show()
        }else{
            var executor = Executors.newSingleThreadExecutor()
            executor.execute(object :Runnable{
                override fun run() {
                    if(activitySignupBinding.rbMale.isChecked){
                        userdetails.gender="Male"
                    }else{
                        userdetails.gender="Female"
                    }
                    usersDao!!.insertuser(userdetails)
                    (context as Activity).runOnUiThread {
                        (context as Activity).finish()
                        Toast.makeText(context, "User Details inserted successfully", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    fun onSelectItem(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
        if (pos == 0) {
            userdetails?.city = ""
        } else {
            userdetails?.city = parent?.selectedItem as String
        }
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun backLogin(view:View){
        (context as Activity).finish()
    }
}