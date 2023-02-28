package com.example.todopeliculas.data.network

import com.example.todopeliculas.data.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val HASH="&hash=1cec1783524a54fc2bb1cbefb48cde99"
private const val APIKEY="&apikey=36592bba299e26f3ec5683a262dcdc43"
private const val TS="&ts=1"
interface ApiService {

    @GET("series?"+TS+APIKEY+HASH)
    suspend fun getMovie(@Query("titleStartsWith") titleStartsWith:String):Response<MovieDataResponse>

}