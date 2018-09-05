package com.example.android.bestphone.phones_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.aafanasev.fonoapi.DeviceEntity
import com.aafanasev.fonoapi.retrofit.FonoApiService
import com.example.android.bestphone.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class PhoneRepository private constructor(val api : FonoApiService) {
    val LOG_TAG = PhoneRepository::class.java.simpleName
    /**The token to connect to the database*/
    val TOKEN = "d11d3c087248bfd8088cd39cd7e84b92218be0ec30e069ad"
    val listOfDevices : MutableLiveData<Resource<List<DeviceEntity>>> = MutableLiveData()
    //a companion object making this class a real singleton
    companion object {
        @Volatile private var INSTANCE : PhoneRepository? = null
        /**Creates a phone repository as a singleton*/
        fun getInstance(api: FonoApiService) : PhoneRepository =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: PhoneRepository(api).also { INSTANCE = it }
                }

    }
    /**Gets a list of phones corresponding to the name of the phone and the brand provided
     * @param phoneName the name of the phone
     *@return a live data that wraps the list of phones corresponding*/
    fun getPossiblePhones(phoneName : String) : LiveData<Resource<List<DeviceEntity>>>{
        //requests the list of phones from internet
        if (!phoneName.isBlank()){
            api.getDevice(token = TOKEN, device = phoneName)
                    .enqueue(object : Callback<List<DeviceEntity>>
                    {
                        override fun onResponse(call: Call<List<DeviceEntity>>?, response: Response<List<DeviceEntity>>?) {
                            listOfDevices.postValue(Resource.success(response?.body() ?: mutableListOf()))
                            Log.v(LOG_TAG, "the devices are fetched")
                        }

                        override fun onFailure(call: Call<List<DeviceEntity>>?, t: Throwable?) {
                            t?.printStackTrace()
                            listOfDevices.postValue(Resource.error(mutableListOf()))
                        }

                    })
        }

        return listOfDevices
    }

}