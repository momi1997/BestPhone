package com.example.android.bestphone.Dagger

import com.aafanasev.fonoapi.retrofit.FonoApiService
import com.example.android.bestphone.phones_list.PhoneRepository
import dagger.Module
import dagger.Provides

@Module
class PhoneRepositoryModule {
    /**Provides the phone repository*/
    @Provides
    fun providePhoneRepository (api : FonoApiService) : PhoneRepository {
        return PhoneRepository.getInstance(api)
    }
}