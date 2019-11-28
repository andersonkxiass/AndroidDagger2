package com.example.tools.features.movies

import com.example.tools.models.Movie
import com.example.tools.models.database.AppDatabase
import com.example.tools.services.ApiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService, private val appDatabase: AppDatabase
) {

    fun getMovies(): Observable<MutableList<Movie>> {
        return Observable.concatArray(getMoviesFromDB(), getMoviesFromAPI())
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

    private fun getMoviesFromAPI(): Observable<MutableList<Movie>> {
        return apiService.get()
            .flatMapIterable { moviesMap ->
                moviesMap.entries.map { mapMovie ->
                    Movie(
                        mapMovie.key,
                        mapMovie.value.rating,
                        mapMovie.value.synopsis,
                        mapMovie.value.title,
                        mapMovie.value.year
                    )
                }
            }
            .toList()
            .doAfterSuccess {
                appDatabase.movieDAO().insertAllMovies(it)
            }
            .toObservable()

    }

    private fun getMoviesFromDB(): Observable<MutableList<Movie>> {
        return Observable.fromCallable {
            appDatabase.movieDAO().getAllMovie()
        }
    }
}