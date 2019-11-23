package com.example.tools.features.movies

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.tools.models.Movie
import com.example.tools.utils.hideKeyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private lateinit var disposable: Disposable

    val data: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    val movieEditing: MutableLiveData<Movie> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {

        disposable = movieRepository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
            .subscribe({ response ->
                data.postValue(response)
            }, { error ->
                Log.d("ListFragment", "error is $error")
            })
    }

    override fun onCleared() {

        if (::disposable.isInitialized) {
            disposable.dispose()
        }
        super.onCleared()
    }

    fun deleteMovie(id: String) {
        movieRepository.deleteMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                val newData = data.value?.filter { movie -> movie.movieId != id }?.toMutableList()
                data.postValue(newData)
            }
            .subscribe()
    }

    fun update(v: View) {
        val movie = movieEditing.value

        movie?.let {
            movieRepository.updateMovie(it.movieId!!, it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    v.hideKeyboard()
                    v.findNavController().popBackStack()
                }
                .subscribe({ response ->
                }, { error ->
                })
        }
    }

    fun cancel(v: View) {
        v.findNavController().popBackStack()
    }
}