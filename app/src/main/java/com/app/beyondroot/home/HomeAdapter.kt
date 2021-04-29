package com.app.beyondroot.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.beyondroot.R
import com.app.beyondroot.database.enitity.UserDetails
import com.app.beyondroot.databinding.AdapterHomeBinding

class HomeAdapter(var userdetails:List<UserDetails>) : RecyclerView.Adapter<HomeAdapter.MyViewModel>() {
    class MyViewModel(var view:AdapterHomeBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
      var inflater = LayoutInflater.from(parent.context)
        var binding:AdapterHomeBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_home,parent,false)
        return MyViewModel(binding)
    }

    override fun getItemCount(): Int {
  return userdetails.size
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
             holder.view.tvNameValue.text=userdetails.get(position).name?:""
             holder.view.tvEmailValue.text=userdetails.get(position).email?:""
             holder.view.tvPhoneValue.text=userdetails.get(position).phonenumber?:""
             holder.view.tvGenderValue.text=userdetails.get(position).gender?:""
             holder.view.tvCityValue.text=userdetails.get(position).city?:""
    }
}