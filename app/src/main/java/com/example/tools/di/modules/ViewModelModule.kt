package com.example.tools.di.modules

import androidx.lifecycle.ViewModel
import com.example.tools.di.ViewModelKey
import com.example.tools.features.movies.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class])
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}