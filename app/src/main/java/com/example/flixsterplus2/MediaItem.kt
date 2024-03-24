package com.example.flixsterplus2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MediaItem (
    // Movie
    @SerializedName("original_title")
    var originalTitle: String? = null, //alternate = original_name

    //TV Show
    @SerializedName("original_name")
    var originalName: String? = null,

    @SerializedName("overview")
    var description: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("first_air_date")
    var firstAirDate: String? = null



) : Serializable {
    val posterImageUrl: String
        get() = posterPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}" }

    val backdropImageUrl: String
        get() = backdropPath.let { path -> "https://image.tmdb.org/t/p/w342/${path}" }

    val title: String
        get() = originalTitle ?: originalName ?: "DEFAULT TITLE"

    val displayDate: String
    get() = when {
        !releaseDate.isNullOrEmpty() -> "Release Date: $releaseDate"
        !firstAirDate.isNullOrEmpty() -> "First Air Date: $firstAirDate"
        else -> "N/A"
    }
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
