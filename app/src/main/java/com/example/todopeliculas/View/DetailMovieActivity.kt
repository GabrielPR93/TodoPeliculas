package com.example.todopeliculas.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.todopeliculas.R
import com.example.todopeliculas.RecyclerView.MovieAdapter
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.data.MovieDetailResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.databinding.ActivityDetailMovieBinding
import com.example.todopeliculas.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: Int = intent.getIntExtra(EXTRA_ID, 0)
        getMovieInformation(id)

    }

    private fun getRetrofit(): Retrofit { //cambiar mas adelante a forma mas optima
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getMovieInformation(id: Int) {
        binding.ProgresBarDetail.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val movieDetail = getRetrofit().create(ApiService::class.java).getDetailMovie(id)
            Log.i("Gabri2", movieDetail.toString())
            if (movieDetail.body() != null) {
                runOnUiThread {
                    createUI(movieDetail.body()!!)
                    binding.ProgresBarDetail.isVisible=false
                }

            }
        }
    }

    private fun createUI(movie: MovieDetailResponse) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.rutaPoster).into(binding.ImageViewDetailMovie)
        binding.textViewNameDetail.text=movie.titulo
        binding.textViewEslogan.text=movie.eslogan
        binding.textViewDescripcion.text=movie.descripcion
        binding.textViewAO.text=movie.fecha
        binding.textViewGeneros.text=movie.generos.joinToString(separator = " · ", transform = {it.nombreGenero})
    }

}
