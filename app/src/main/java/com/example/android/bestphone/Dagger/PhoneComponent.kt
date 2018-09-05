package com.example.android.bestphone.Dagger

import com.example.android.bestphone.phones_list.PhoneAdapter
import com.example.android.bestphone.phones_list.PhoneViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkingModule::class, PhoneModelModule::class, PhoneRepositoryModule::class, ContextModule::class])
interface PhoneComponent {
    fun getPhoneModel () : PhoneViewModel
    fun getPhoneAdapter(): PhoneAdapter
}