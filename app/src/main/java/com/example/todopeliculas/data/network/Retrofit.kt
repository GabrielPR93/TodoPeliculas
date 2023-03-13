package com.example.todopeliculas.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object{

         fun getRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}