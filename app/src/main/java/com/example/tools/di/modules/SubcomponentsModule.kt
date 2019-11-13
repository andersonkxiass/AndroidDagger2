package com.example.tools.di.modules

import com.example.tools.di.components.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class])
class SubcomponentsModule {}