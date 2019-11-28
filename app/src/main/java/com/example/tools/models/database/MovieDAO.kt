package com.example.tools.models.database

import androidx.room.*
import com.example.tools.models.Movie


@Dao
interface MovieDAO {

    @Query("SELECT * FROM movies")
    fun getAllMovie(): MutableList<Movie>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun getMovieById(id : String): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg users: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: MutableList<Movie>)

    @Update
    fun updateMovie(user: Movie)

    @Delete
    fun deleteMovie(user: Movie)

    @Query("DELETE FROM movies")
    fun deleteAll()
}