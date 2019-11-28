package com.example.tools.di.modules

import com.example.tools.di.components.MovieComponent
import dagger.Module

@Module(subcomponents = [MovieComponent::class])
class SubcomponentsModule {}