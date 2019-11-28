package com.example.tools.features.movies

import com.example.tools.models.Movie
import com.example.tools.models.database.AppDatabase
import com.example.tools.services.ApiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    fun getMovies(): Observable<Map<String, Movie>> {
        return apiService.get()
    }

    fun updateMovie(movieId: String, movie: Movie): Single<Movie> {
        return apiService.put(movieId, movie)
    }

    fun deleteMovie(movieId: String): Completable {
        return apiService.delete(movieId)
    }

    fun createMovie(movie: Movie): Single<String> {
        return apiService.post(movie)
    }

}