package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class TrendingDataResponse(
    @SerializedName("results")var moviesTrending: List<MovieTrendingItemResponse>

)
data class MovieTrendingItemResponse(
    @SerializedName("id")var id: Int,
    @SerializedName("poster_path")var poster: String,


)