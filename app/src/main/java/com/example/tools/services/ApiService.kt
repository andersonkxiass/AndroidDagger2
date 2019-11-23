package com.example.tools.services

import com.example.tools.models.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("/movies.json")
    fun get(): Observable<Map<String, Movie>>

    @PUT("/movies/{id}.json")
    fun put(@Path("id") id: String, @Body movie: Movie): Single<Movie>

    @DELETE("/movies/{id}.json")
    fun delete(@Path("id") id: String): Completable

    @POST("/movies.json")
    fun post(@Body movie: Movie): Single<String>
}