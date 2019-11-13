package com.example.tools.di.components

import com.example.tools.MainActivity
import com.example.tools.di.scopes.ActivityScope
import com.example.tools.features.login.LoginFragment
import dagger.Subcomponent


@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: MainActivity)
    fun inject(usernameFragment: LoginFragment)
}