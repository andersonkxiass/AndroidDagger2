package com.example.tools.di.components

import android.content.Context
import com.example.tools.MainActivity
import com.example.tools.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        SubcomponentsModule::class,
        ViewModelModule::class,
        DatabaseModule::class]
)
interface ApplicationComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun movieComponent(): MovieComponent.Factory

    fun inject(activity: MainActivity)
}