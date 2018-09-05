package com.example.android.bestphone.Dagger

import com.aafanasev.fonoapi.retrofit.FonoApiFactory
import com.aafanasev.fonoapi.retrofit.FonoApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkingModule (){
    /**Provides the fono api object*/
    @Provides
    fun provideFonoApi(client : OkHttpClient): FonoApiService {
        return FonoApiFactory().create(client)
    }

    /**Provides the Http client*/
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()

    }

    /**Provides an interceptor for http client*/
    @Provides
    fun provideInterceptor () : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }

    @Provides
    /**Provides a Gson object for serialization and deserialization*/
    fun provideGson () : Gson {
        return Gson()
    }
}