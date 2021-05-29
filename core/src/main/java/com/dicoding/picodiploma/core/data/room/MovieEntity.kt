package com.dicoding.picodiploma.core.data.room

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movieentities")
data class MovieEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "movieId")
        var movieId: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "date")
        var date: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "duration")
        var duration: String,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "favorited")
        var favorited: Boolean = false
    ): Parcelable