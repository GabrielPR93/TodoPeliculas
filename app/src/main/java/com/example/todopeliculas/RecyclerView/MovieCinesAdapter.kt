package com.example.todopeliculas.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.R
import com.example.todopeliculas.data.CinesItemResponse

class MovieCinesAdapter (var movieCinesList: List<CinesItemResponse> = emptyList(), private val onItemSelected:(Int)-> Unit):
    RecyclerView.Adapter<MovieCinesViewHolder>()  {

    fun updateList(movieCinesList: List<CinesItemResponse>){
        this.movieCinesList=movieCinesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCinesViewHolder {
       return MovieCinesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cines,parent,false))
    }

    override fun onBindViewHolder(holder: MovieCinesViewHolder, position: Int) {
      holder.bind(movieCinesList[position],onItemSelected)
    }

    override fun getItemCount()=movieCinesList.size
}