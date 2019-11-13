package com.example.tools.di.components

import com.example.tools.MainActivity
import com.example.tools.di.modules.NetworkModule
import com.example.tools.di.modules.SubcomponentsModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun loginComponent(): LoginComponent.Factory

    fun inject(activity: MainActivity)
}