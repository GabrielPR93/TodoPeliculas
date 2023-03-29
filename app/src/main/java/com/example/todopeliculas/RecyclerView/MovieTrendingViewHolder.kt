package com.example.todopeliculas.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.MovieTrendingItemResponse
import com.example.todopeliculas.databinding.ItemMovieTrendingBinding
import com.squareup.picasso.Picasso

class MovieTrendingViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieTrendingBinding.bind(view)

    fun bind(movieTrendingItemResponse: MovieTrendingItemResponse, onItemSelected:(Int)->Unit){

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movieTrendingItemResponse.poster).into(binding.ImageViewMovieTrending)
        binding.root.setOnClickListener { onItemSelected(movieTrendingItemResponse.id) }
    }


}