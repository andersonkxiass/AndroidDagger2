package com.example.tools.features.movies

import com.example.tools.models.database.AppDatabase
import com.example.tools.models.database.MovieDB
import io.reactivex.Observable
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getMovies(): Observable<List<MovieDB>> {

        return Observable.fromCallable {
            appDatabase.movieDAO().getAllMovie()
        }
    }

    fun getMovieById(id: String): Observable<MovieDB> {
        return Observable.fromCallable{appDatabase.movieDAO().getMovieById(id)}
    }

    fun updateMovie(movie: MovieDB): Observable<Unit> {
        return Observable.fromCallable{appDatabase.movieDAO().updateMovie(movie)}
    }

    fun deleteMovie(movie: MovieDB): Observable<Unit> {
        return Observable.fromCallable{appDatabase.movieDAO().deleteMovie(movie)}
    }

    fun createMovie(movie: MovieDB): Observable<Unit> {
        return Observable.fromCallable{appDatabase.movieDAO().insertMovie(movie)}
    }
}