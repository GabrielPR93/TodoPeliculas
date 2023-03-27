package com.example.todopeliculas.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.RecyclerView.ActorAdapter
import com.example.todopeliculas.View.DetailActorActivity.Companion.ID_ACTOR
import com.example.todopeliculas.data.MovieDetailResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.data.network.Retrofit.Companion.getRetrofit
import com.example.todopeliculas.databinding.ActivityDetailMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

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
        CoroutineScope(Dispatchers.IO).launch {
            val movieDetail = retrofit.create(ApiService::class.java).getDetailMovie(id)

            if (movieDetail.body() != null) {
                runOnUiThread {
                    createUI(movieDetail.body()!!)

                }

            }
        }
    }

    private fun getActorsMovie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            val actorMovie = retrofit.create(ApiService::class.java).getCreditsMovie(id)
            val response = actorMovie.body()

            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.actores)

                }
            }
        }
    }

    private fun createUI(movie: MovieDetailResponse) {

        if(!movie.rutaPoster.isNullOrEmpty()){
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.rutaPoster).into(binding.ImageViewDetailMovie)
        }
        binding.textViewNameDetail.text=movie.titulo?:""
        binding.ratingBar.rating=movie.mediaVotos.toFloat()/2
        binding.textViewEslogan.text=movie.eslogan?:""
        binding.textViewDescripcion.text=movie.descripcion?:""
        binding.textViewAO.text=movie.fecha?:""
        binding.textViewGeneros.text=movie.generos.joinToString(separator = " · ", transform = {it.nombreGenero})?:""

    }

    private fun InitReciclerView(){
        adapter= ActorAdapter{idActor -> navigateToDetailActor(idActor)}
        binding.reciclerViewActores.setHasFixedSize(true)
        binding.reciclerViewActores.layoutManager= LinearLayoutManager(this,
            RecyclerView.HORIZONTAL,false)
        binding.reciclerViewActores.adapter=adapter
    }

    private fun navigateToDetailActor(id:Int){
        val intent= Intent(this,DetailActorActivity::class.java)
        intent.putExtra(ID_ACTOR,id)
        startActivity(intent)
    }
}
