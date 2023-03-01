package com.example.todopeliculas.RecyclerView


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movieItemResponse: MovieDataResponse.MovieItemResponse) {

        binding.tvMovieName.text=movieItemResponse.MovieName
        binding.tvStartYear.text=movieItemResponse.StartYear.toString()

        Picasso.get().load(movieItemResponse.Thumbnail.UrlImage+".jpg").into(binding.ImageViewMovie) //Cargar Imagen

    }
}