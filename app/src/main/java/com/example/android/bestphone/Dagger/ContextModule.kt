package com.example.android.bestphone.Dagger

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ContextModule (activity : AppCompatActivity) {
    val activity = activity
    /**Provides activity context*/
    @Provides
    fun provideActivity () : AppCompatActivity {
        return activity
    }
}