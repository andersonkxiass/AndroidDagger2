package com.example.tools.models.database

import androidx.room.*


@Dao
interface MovieDAO {

    @Query("SELECT * FROM movies")
    fun getAllMovie(): List<MovieDB>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun getMovieById(id : String): MovieDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg users: MovieDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: MutableList<MovieDB>)

    @Update
    fun updateMovie(user: MovieDB)

    @Delete
    fun deleteMovie(user: MovieDB)

    @Query("DELETE FROM movies")
    fun deleteAll()
}