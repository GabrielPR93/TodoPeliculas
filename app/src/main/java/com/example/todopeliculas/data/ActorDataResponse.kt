package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class ActorDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val actores: List<ActorItemResponse>
)

data class ActorItemResponse(
    @SerializedName("original_name") val name: String,
    @SerializedName("profile_path") val rutaImagen: String
)
