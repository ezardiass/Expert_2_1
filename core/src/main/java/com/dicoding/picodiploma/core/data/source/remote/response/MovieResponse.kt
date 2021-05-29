package com.dicoding.picodiploma.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
        @field:SerializedName("id")
        var id:String,
        @field:SerializedName("original_title")
        var title: String,
        @field:SerializedName("release_date")
        var date: String,
        @field:SerializedName("overview")
        var description: String,
        @field:SerializedName("vote_average")
        var duration: String,
        @field:SerializedName("poster_path")
        var image: String
)

