package com.example.todopeliculas.data.network

import com.example.todopeliculas.data.ActorDataResponse
import com.example.todopeliculas.data.ActorDetailDataResponse
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.data.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val APIKEY="api_key=dd25ec6365ddb50099ea71752bb96cce"

interface ApiService {

    @GET("search/movie?"+APIKEY+"&language=es-Es")
    suspend fun getMovie(@Query("query") title:String):Response<MovieDataResponse>

    @GET("movie/{id}?"+APIKEY+"&language=es-Es")
    suspend fun getDetailMovie(@Path("id") idMovie:Int):Response<MovieDetailResponse>

    @GET("movie/{id}/credits?"+ APIKEY+"&language=es-Es")
    suspend fun getCreditsMovie(@Path("id") idMovie: Int):Response<ActorDataResponse>

    @GET("person/{id}?"+ APIKEY+"&language=es-Es")
    suspend fun getDetailActor(@Path("id") idMovie: Int):Response<ActorDetailDataResponse>

}