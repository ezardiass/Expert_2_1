package com.dicoding.picodiploma.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun dao(): FilmDao

}