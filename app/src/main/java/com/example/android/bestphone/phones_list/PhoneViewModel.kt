package com.example.android.bestphone.phones_list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aafanasev.fonoapi.DeviceEntity
import com.example.android.bestphone.Resource

class PhoneViewModel (private val phoneRepo : PhoneRepository) : ViewModel() {
    /**A list of phones wrapped by livedata*/
    var phones : MutableLiveData<Resource<List<DeviceEntity>>> = MutableLiveData()
    init {
        phoneRepo.listOfDevices.observeForever {
            phones.value = it ?: Resource.new(mutableListOf())
        }
    }
    /**Updates the list of phones in the the view model by fetching a new list from the internet
     * and returns it
     * @param phoneName the name of the phone the user is looking for
     */
    fun getPossiblePhones (phoneName : String) {
        phoneRepo.getPossiblePhones(phoneName)

    }


}