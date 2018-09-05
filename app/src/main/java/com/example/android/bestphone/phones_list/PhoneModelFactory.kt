package com.example.android.bestphone.phones_list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class PhoneModelFactory @Inject constructor(val phoneRepo : PhoneRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhoneViewModel::class.java)){
            return PhoneViewModel(phoneRepo) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}