package com.example.tools.di.modules

import com.example.tools.features.login.LoginRepository
import com.example.tools.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideApiRetrofitService(): ApiService {

        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideLoginRepository(apiService: ApiService): LoginRepository {
        return LoginRepository(apiService)
    }
}