package com.example.tools.di.modules

import com.example.tools.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = ""
    }

    @Provides
    @Reusable
    fun provideApiRetrofitService(): ApiService {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}