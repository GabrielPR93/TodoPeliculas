package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class ActorDetailDataResponse(
    @SerializedName("biography") val biography: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("deathday") val deathday: String,
    //@SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("known_for_department") val department: String,
    @SerializedName("name") val name: String,
    @SerializedName("place_of_birth") val place_birth: String,
    //@SerializedName("popularity") val popularity: Int,
    @SerializedName("profile_path") val path: String
){}