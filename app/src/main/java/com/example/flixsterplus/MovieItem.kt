package com.example.flixsterplus

import com.google.gson.annotations.SerializedName


class MovieItem {
    @SerializedName("original_title")
    var title: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    val posterImageUrl : String
        get() = posterPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}"}

    val backdropImageUrl : String
        get()  = backdropPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}"}


}