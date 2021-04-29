package com.app.beyondroot.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.beyondroot.database.enitity.UserDetails

@Dao
interface UsersDao {

    @Insert
    fun insertuser(userdata:UserDetails)

    @Query("Select * from user_details order by id DESC")
     fun getUsers(): List<UserDetails>

    @Query("Select COUNT(*) from user_details where phonenumber=:phonenumber")
    fun getPhoneUsers(phonenumber:String) : Int

    @Query("Select COUNT(*) from user_details where email=:email COLLATE NOCASE")
    fun getEmailUsers(email:String) : Int


    @Query("Select COUNT(*) from user_details where password=:password")
    fun getPassword(password:String) : Int

    @Query("Select COUNT(*) from user_details where email=:email and password=:password")
    fun checkCredentials(email: String, password: String):Int
}