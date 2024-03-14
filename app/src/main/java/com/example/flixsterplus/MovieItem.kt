package com.example.flixsterplus
import com.google.gson.annotations.SerializedName


class MovieItem {
    @SerializedName("original_title")
    var title: String? = null
    @SerializedName("overview")
    var description: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null



}