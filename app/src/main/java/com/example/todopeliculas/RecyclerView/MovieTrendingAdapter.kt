package com.example.todopeliculas.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.R
import com.example.todopeliculas.data.MovieTrendingItemResponse

class MovieTrendingAdapter(var movieTrendingList: List<MovieTrendingItemResponse> = emptyList(),private val onItemSelected:(Int) -> Unit):RecyclerView.Adapter<MovieTrendingViewHolder>() {

    fun updateList(movieTrendingList: List<MovieTrendingItemResponse>){
        this.movieTrendingList=movieTrendingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrendingViewHolder {
       return MovieTrendingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_trending,parent,false))
    }

    override fun onBindViewHolder(holder: MovieTrendingViewHolder, position: Int) {
       holder.bind(movieTrendingList[position],onItemSelected)
    }

    override fun getItemCount()=movieTrendingList.size
}