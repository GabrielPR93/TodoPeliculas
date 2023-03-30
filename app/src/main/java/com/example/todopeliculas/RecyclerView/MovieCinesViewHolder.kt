package com.example.todopeliculas.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.CinesItemResponse
import com.example.todopeliculas.databinding.ItemCinesBinding
import com.squareup.picasso.Picasso

class MovieCinesViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = ItemCinesBinding.bind(view)

    fun bind(cinesItemResponse: CinesItemResponse, onItemSelected: (Int) -> Unit) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+cinesItemResponse.rutaPoster).into(binding.ImageViewCines)
        binding.root.setOnClickListener { onItemSelected(cinesItemResponse.id) }
    }


}