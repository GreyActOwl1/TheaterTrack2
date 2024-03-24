package com.example.flixsterplus2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MediaItem (
    // Movie
    var original_title: String? = null, //alternate = original_name

    //TV Show
    var original_name: String? = null,

    @SerializedName("overview")
    var description: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,


    var first_air_date: String? = null
) : Serializable {
    val posterImageUrl : String
        get() = posterPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}"}

    val backdropImageUrl : String
        get()  = backdropPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}"}

    val title : String
        get() = original_title ?: original_name ?: "DEFAULT TITLE"

    val lastAirDate : String
        get() = first_air_date.let { date -> "First Air Date: ${date}" }
}

/*
    // Movie
    var original_title: String? = null

    //TV Show
    var original_name: String? = null

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

    val title : String
        get() = original_title ?: original_name ?: "DEFAULT TITLE"

*/
