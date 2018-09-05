package com.example.android.bestphone.Dagger

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.example.android.bestphone.phones_list.PhoneModelFactory
import com.example.android.bestphone.phones_list.PhoneViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class PhoneModelModule {
    /**provides the view model*/
    @Provides
    fun providePhoneModel (activity: AppCompatActivity, factory: PhoneModelFactory) : PhoneViewModel {
        return ViewModelProviders.of(activity, factory).get(PhoneViewModel::class.java)
    }
}