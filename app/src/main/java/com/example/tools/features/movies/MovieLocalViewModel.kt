package com.example.tools.features.movies

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.tools.features.movies.list.ListFragmentDirections
import com.example.tools.models.database.MovieDB
import com.example.tools.utils.hideKeyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieLocalViewModel @Inject constructor(private val movieLocalRepository: MovieLocalRepository) :
    ViewModel() {

    private lateinit var deleteDisposable: Disposable
    private lateinit var disposable: Disposable

    val data: MutableLiveData<List<MovieDB>> = MutableLiveData()

    val movieEditing: MutableLiveData<MovieDB> = MutableLiveData(MovieDB(""))

    val movieCreating: MutableLiveData<MovieDB> = MutableLiveData(MovieDB(""))

    init {
        loadData()
    }

    private fun loadData() {
        disposable = movieLocalRepository
            .getMovies()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                data.postValue(movies)
            }, { error -> })
    }

    fun deleteMovie(id: String) {
        deleteDisposable = movieLocalRepository
            .getMovieById(id)
            .flatMap { movie -> movieLocalRepository.deleteMovie(movie) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadData()
            }, { error -> })
    }

    fun update(v: View) {

        val movieDB = movieEditing.value

        movieDB?.let {
            movieLocalRepository
                .updateMovie(it)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    movieEditing.postValue(MovieDB(""))
                    v.hideKeyboard()
                    v.findNavController().popBackStack()
                }
                .subscribe({
                    loadData()
                }, { error -> })
        }
    }

    fun create(v: View) {

        val movieDB = movieCreating.value

        movieDB?.let {

            movieDB.movieId = UUID.randomUUID().toString()

            movieLocalRepository
                .createMovie(movieDB)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    movieCreating.postValue(MovieDB(""))
                    v.hideKeyboard()
                    v.findNavController().popBackStack()
                }
                .subscribe({
                    loadData()
                }, { error -> })
        }
    }

    fun novo(v: View) {
        v.findNavController().navigate(ListFragmentDirections.navToCreate())
    }

    fun cancel(v: View) {
        v.hideKeyboard()
        v.findNavController().popBackStack()
    }

    override fun onCleared() {

        if (::disposable.isInitialized) {
            disposable.dispose()
        }

        if (::deleteDisposable.isInitialized) {
            deleteDisposable.dispose()
        }

        super.onCleared()
    }
}