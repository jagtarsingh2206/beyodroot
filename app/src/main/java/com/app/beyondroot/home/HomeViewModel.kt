package com.app.beyondroot.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.beyondroot.Constants
import com.app.beyondroot.database.BeyodrootDatabase
import com.app.beyondroot.database.dao.UsersDao
import com.app.beyondroot.database.enitity.UserDetails
import com.app.beyondroot.databinding.ActivityHomeBinding
import com.app.beyondroot.login.LoginActivity

class HomeViewModel(
    var context: HomeActivity,
    var activityHomeBinding: ActivityHomeBinding
) : ViewModel(){
    var beyodrootDatabase: BeyodrootDatabase?=null
    var usersDao: UsersDao?=null
    init {
        beyodrootDatabase= BeyodrootDatabase.getInstance(context)
        usersDao=beyodrootDatabase!!.userDao
        var userdetails:List<UserDetails> = usersDao!!.getUsers()
        activityHomeBinding.rvUserdetails.layoutManager=LinearLayoutManager(context)
        activityHomeBinding.rvUserdetails.adapter=HomeAdapter(userdetails)
    }
    fun logout(view:View){
     var alertDialog=AlertDialog.Builder(context)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to logout?")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                var sharedpreferences=context.getSharedPreferences(Constants.PREFNAME,Context.MODE_PRIVATE)
                var editor=sharedpreferences.edit()
                editor.putBoolean(Constants.ISLOGIN,false)
                editor.commit()
                var intent = Intent(context,LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                (context as Activity).startActivity(intent)
            }
        })
        alertDialog.setNegativeButton("No",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0?.dismiss()
            }
        })
        alertDialog.show()
    }
}