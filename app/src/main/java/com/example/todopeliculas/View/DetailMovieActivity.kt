package com.example.todopeliculas.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.view.iterator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.RecyclerView.ActorAdapter
import com.example.todopeliculas.data.ActorItemResponse
import com.example.todopeliculas.data.MovieDetailResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.data.network.Retrofit.Companion.getRetrofit
import com.example.todopeliculas.databinding.ActivityDetailMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: ActorAdapter
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit= getRetrofit()
        InitReciclerView()

        val id: Int = intent.getIntExtra(EXTRA_ID, 0)
        getMovieInformation(id)
        getActorsMovie(id)

    }

    private fun getMovieInformation(id: Int) {
        binding.ProgresBarDetail.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val movieDetail = retrofit.create(ApiService::class.java).getDetailMovie(id)
            Log.i("Gabri2", movieDetail.toString())
            if (movieDetail.body() != null) {
                runOnUiThread {
                    createUI(movieDetail.body()!!)
                    binding.ProgresBarDetail.isVisible=false
                }

            }
        }
    }

    private fun getActorsMovie(id: Int) {
        binding.ProgresBarDetail.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val actorMovie = retrofit.create(ApiService::class.java).getCreditsMovie(id)
            val response = actorMovie.body()
            Log.i("Gabri2", response.toString())
            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.actores)
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
        binding.textViewGeneros.text=movie.generos.joinToString(separator = " ?? ", transform = {it.nombreGenero})

    }

    private fun InitReciclerView(){
        adapter= ActorAdapter()
        binding.reciclerViewActores.setHasFixedSize(true)
        binding.reciclerViewActores.layoutManager= LinearLayoutManager(this,
            RecyclerView.HORIZONTAL,false)
        binding.reciclerViewActores.adapter=adapter
    }
}
