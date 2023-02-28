package com.example.todopeliculas.RecyclerView


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.databinding.ItemMovieBinding

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movieItemResponse: MovieDataResponse.MovieItemResponse) {

        binding.tvMovieName.text=movieItemResponse.MovieName
    }
}