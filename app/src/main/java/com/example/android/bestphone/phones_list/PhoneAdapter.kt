package com.example.android.bestphone.phones_list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aafanasev.fonoapi.DeviceEntity
import com.example.android.bestphone.DetailActivity
import com.example.android.bestphone.R
import com.google.gson.Gson
import javax.inject.Inject

class PhoneAdapter @Inject constructor(val context : AppCompatActivity, val gson : Gson) : Adapter<PhoneAdapter.PhoneViewHolder>() {


    var dataSet : List<DeviceEntity> = mutableListOf<DeviceEntity>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    //creates the phone view holder class
    class PhoneViewHolder (val view : View) : RecyclerView.ViewHolder(view)
    //creates the view holder for each view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        //inflates the view holder
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.phone_view, parent, false)
        return PhoneViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        //gets the phone at the ith position
        val phone = dataSet.get(position)
        //updates the view holder name
        holder.view.findViewById<TextView>(R.id.name).text = "${phone.deviceName}"
        //updates the view price
        holder.view.findViewById<TextView>(R.id.price).text = "${phone.brand}"
        //sets the ClickListener
        holder.view.setOnClickListener{
            //serializes the DeviceEntity object into a json string
            val json = gson.toJson(phone)
            //creates an intent to start the DetailActivity
            val intent = Intent(context, DetailActivity::class.java)
            //sets the json as an extra
            intent.putExtra(context.getString(R.string.phone_key), json)
            //starts the activity with the intent
            context.startActivity(intent)
        }
    }


}