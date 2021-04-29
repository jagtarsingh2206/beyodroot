package com.app.beyondroot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.app.beyondroot.databinding.ActivityMainBinding
import com.app.beyondroot.home.HomeActivity
import com.app.beyondroot.login.LoginActivity

class SplashActivity : AppCompatActivity() {
     lateinit var activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                var sharedpreferences= getSharedPreferences(Constants.PREFNAME, Context.MODE_PRIVATE)
                 var isLogin=sharedpreferences.getBoolean(Constants.ISLOGIN,false)
                if(isLogin==true){
                    var intent=Intent(this@SplashActivity,HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }else{
                    var intent=Intent(this@SplashActivity,LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }

        },3000)
    }
}