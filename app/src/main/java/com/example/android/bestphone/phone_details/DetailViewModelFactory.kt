package com.example.android.bestphone.phone_details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aafanasev.fonoapi.DeviceEntity

class DetailViewModelFactory(val device : DeviceEntity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(device) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}