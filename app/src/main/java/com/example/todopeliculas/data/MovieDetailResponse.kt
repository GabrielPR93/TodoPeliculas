package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @SerializedName("title") val titulo: String,
    @SerializedName("tagline") val eslogan: String,
    //@SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val rutaPoster: String,
    @SerializedName("overview") val descripcion: String,
    //@SerializedName("popularity") val popularidad: Int,
    @SerializedName("release_date") val fecha: String,
    @SerializedName("genres") val generos: List<MovieGenres>

)

data class MovieGenres(
    @SerializedName("name") val nombreGenero: String,
    @SerializedName("id") val idGenero: Int
)