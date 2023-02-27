package com.example.todopeliculas.data.network

import com.example.todopeliculas.data.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ApiService {

    @GET("series?titleStartsWith={name}&ts=1&apikey=36592bba299e26f3ec5683a262dcdc43&hash=1cec1783524a54fc2bb1cbefb48cde99")
    suspend fun getMovie(@Path("name") movieName:String):Response<MovieDataResponse>

}