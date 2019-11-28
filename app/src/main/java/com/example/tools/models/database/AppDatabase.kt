package com.example.tools.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tools.models.Movie

@Database(version = 1, entities = [Movie::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}
