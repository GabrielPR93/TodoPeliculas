package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val Total_results: Int,
    @SerializedName("results") val Movies: List<MovieItemResponse>
) {

    }

    data class MovieItemResponse(
        @SerializedName("id") val MovieId: Int,
        @SerializedName("title") val MovieName: String,
        @SerializedName("release_date") val release: String,
        @SerializedName("poster_path") val URLImage: String,

    ) {

    }

