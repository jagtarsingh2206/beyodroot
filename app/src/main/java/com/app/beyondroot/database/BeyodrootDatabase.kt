package com.app.beyondroot.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.app.beyondroot.database.dao.UsersDao
import com.app.beyondroot.database.enitity.UserDetails

@Database(entities = [UserDetails::class],version = 1)
abstract class BeyodrootDatabase : RoomDatabase(){
abstract val userDao:UsersDao
    companion object{
        @Volatile
        private var INSTANCE : BeyodrootDatabase?=null
        fun getInstance(context: Context):BeyodrootDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context,BeyodrootDatabase::class.java,"beyondroot_database").allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }

}