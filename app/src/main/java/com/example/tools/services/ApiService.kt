package com.example.tools.services

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/list")
    fun get(): Call<Any>
}