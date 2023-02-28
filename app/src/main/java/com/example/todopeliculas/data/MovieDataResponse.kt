package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class MovieDataResponse(
    @SerializedName("code") val codeStatus: Int,
    @SerializedName("data") val data: MovieData

) {

    data class MovieData(
        @SerializedName("count") val Count: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("results") val Movies: List<MovieItemResponse>
    ) {

    }

    data class MovieItemResponse(
        @SerializedName("id") val MovieId: Int,
        @SerializedName("title") val MovieName: String
    ) {

    }


}
