package com.app.beyondroot.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.beyondroot.Constants
import com.app.beyondroot.database.BeyodrootDatabase
import com.app.beyondroot.database.dao.UsersDao
import com.app.beyondroot.database.enitity.UserDetails
import com.app.beyondroot.home.HomeActivity
import com.app.beyondroot.signup.SignUpActivity

class LoginViewModel(var context: LoginActivity) : ViewModel(){
    var beyodrootDatabase:BeyodrootDatabase?=null
    var usersDao:UsersDao?=null

    init {
        beyodrootDatabase= BeyodrootDatabase.getInstance(context)
        usersDao=beyodrootDatabase!!.userDao
    }

    var userDetails=UserDetails(0,"","","","","","")
    fun login(view: View){
        if(userDetails.email.trim().length>0){
            if(isEmailValid(userDetails.email)){
             if(userDetails.password.trim().length>0){
                 var count = usersDao!!.checkCredentials(userDetails.email,userDetails.password)
                 if(count>0){
                     var sharedpreferences=context.getSharedPreferences(Constants.PREFNAME,Context.MODE_PRIVATE)
                     var editor=sharedpreferences.edit()
                     editor.putBoolean(Constants.ISLOGIN,true)
                     editor.commit()
                     var intent = Intent(context,HomeActivity::class.java)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                     (context as Activity).startActivity(intent)
                     Toast.makeText(context, "Successfully Login", Toast.LENGTH_LONG).show()
                 }else{
                     Toast.makeText(context, "Wrong email address and password", Toast.LENGTH_LONG).show()
                 }
             }else{
                 Toast.makeText(context, "Password Required", Toast.LENGTH_LONG).show()
             }
            }else{
                Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context, "Email required", Toast.LENGTH_LONG).show()
        }
    }

    fun signUp(view:View){
      var intent = Intent(context,SignUpActivity::class.java)
        (context as Activity).startActivity(intent)
    }
    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}