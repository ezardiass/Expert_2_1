package com.dicoding.picodiploma.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie (
    var movieId: String,
    var title: String,
    var date: String,
    var description: String,
    var duration: String,
    var image: String,
    var favorited: Boolean = false
): Parcelable