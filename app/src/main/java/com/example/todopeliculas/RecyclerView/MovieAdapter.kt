package com.example.todopeliculas.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.R
import com.example.todopeliculas.data.MovieItemResponse

class MovieAdapter(var movieList: List<MovieItemResponse> = emptyList(),
                   private val onItemSelected: (Int) -> Unit):RecyclerView.Adapter<MovieViewHolder>() {

    fun updateList(movieList: List<MovieItemResponse>){
        this.movieList=movieList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(movieList[position],onItemSelected)

    }

    override fun getItemCount() = movieList.size


}