package com.example.todopeliculas

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    val HASH= "1cec1783524a54fc2bb1cbefb48cde99"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit=getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse=retrofit.create(ApiService::class.java).getMovie(query)
            if (myResponse.isSuccessful){
                Log.i("Gabri","Funciona !!!!")
                val response: MovieDataResponse? =myResponse.body()
                if(response!=null){
                    Log.i("Gabri",response.toString())
                }

            }else{
                Log.i("Gabri","NOOOOOOO ;((")
            }
        }

    }


    private fun getRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}