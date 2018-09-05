package com.example.android.bestphone.phone_details


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aafanasev.fonoapi.DeviceEntity

class DetailViewModel (val device : DeviceEntity) : ViewModel() {
    val phone : MutableLiveData<DeviceEntity> = MutableLiveData()
    init {
        phone.value = device
    }
}