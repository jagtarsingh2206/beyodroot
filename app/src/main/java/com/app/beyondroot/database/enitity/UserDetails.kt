package com.app.beyondroot.database.enitity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.beyondroot.BR

@Entity(tableName = "user_details")
class UserDetails(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var email:String,
    var phonenumber:String,
    var gender:String,
    var city:String,
    var password:String
) : BaseObservable(){
    var _name:String
    @Bindable get() = name
    set(value) {
        this.name=value
        notifyPropertyChanged(BR._name)

    }
    var _email:String
        @Bindable get() = email
        set(value) {
            this.email=value
            notifyPropertyChanged(BR._email)
        }
    var _phoneNumber:String
        @Bindable get() = phonenumber
        set(value) {
            this.phonenumber=value
            notifyPropertyChanged(BR._phoneNumber)
        }
    var _password:String
        @Bindable get() = password
        set(value) {
            this.password=value
            notifyPropertyChanged(BR._password)
        }
}