package com.example.tools.models.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [MovieDB::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}
