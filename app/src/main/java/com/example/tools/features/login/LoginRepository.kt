package com.example.tools.features.login

import com.example.tools.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val apiService: ApiService) {

    fun getData() {
        apiService.get()
            .enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {

                }

                override fun onResponse(call: Call<Any>, response: Response<Any>) {

                }

            })
    }
}