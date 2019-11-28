package com.example.tools.di.modules

import android.content.Context
import androidx.room.Room
import com.example.tools.models.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule{

    @Provides
    @Singleton
    fun providesRoom(context: Context) : AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database-name")
            .build()
    }
}