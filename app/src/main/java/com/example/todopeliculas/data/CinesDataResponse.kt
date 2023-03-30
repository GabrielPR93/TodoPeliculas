package com.example.todopeliculas.data

import com.google.gson.annotations.SerializedName

data class CinesDataResponse(
    @SerializedName("results")var moviesCines: List<CinesItemResponse>
){

}

data class CinesItemResponse(
    @SerializedName("id")var id: Int,
    @SerializedName("poster_path") var rutaPoster: String
){

}