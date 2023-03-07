package com.example.todopeliculas.data.network

import com.example.todopeliculas.data.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val APIKEY="api_key=dd25ec6365ddb50099ea71752bb96cce"

interface ApiService {

    @GET("search/movie?"+APIKEY)
    suspend fun getMovie(@Query("query") title:String):Response<MovieDataResponse>

}