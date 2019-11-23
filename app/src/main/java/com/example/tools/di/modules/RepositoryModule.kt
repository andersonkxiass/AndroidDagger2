package com.example.tools.di.modules

import com.example.tools.features.movies.MovieRepository
import com.example.tools.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(apiService: ApiService): MovieRepository {
        return MovieRepository(apiService)
    }
}