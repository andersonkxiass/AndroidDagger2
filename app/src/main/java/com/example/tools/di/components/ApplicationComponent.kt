package com.example.tools.di.components

import com.example.tools.MainActivity
import com.example.tools.di.modules.NetworkModule
import com.example.tools.di.modules.SubcomponentsModule
import com.example.tools.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun movieComponent(): MovieComponent.Factory

    fun inject(activity: MainActivity)
}