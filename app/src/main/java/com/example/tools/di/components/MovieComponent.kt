package com.example.tools.di.components

import android.content.Context
import com.example.tools.di.scopes.ActivityScope
import com.example.tools.features.movies.create.CreateFragment
import com.example.tools.features.movies.delete.DeleteFragment
import com.example.tools.features.movies.list.ListFragment
import com.example.tools.features.movies.update.UpdateFragment
import dagger.BindsInstance
import dagger.Subcomponent


@ActivityScope
@Subcomponent
interface MovieComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MovieComponent
    }

    fun inject(listFragment: ListFragment)
    fun inject(deleteFragment: DeleteFragment)
    fun inject(updateFragment: UpdateFragment)
    fun inject(createFragment: CreateFragment)
}