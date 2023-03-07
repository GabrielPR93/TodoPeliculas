package com.example.todopeliculas.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.MovieItemResponse
import com.example.todopeliculas.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movieItemResponse: MovieItemResponse, onItemSelected:(Int)-> Unit) {

        binding.tvMovieName.text=movieItemResponse.MovieName
        //binding.tvStartYear.text=movieItemResponse.release

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movieItemResponse.URLImage).into(binding.ImageViewMovie) //Cargar Imagen (URLImage: solo me devuelve el ultimo fragmento de la url en esta API )
        binding.root.setOnClickListener { onItemSelected(movieItemResponse.MovieId)}
    }
}