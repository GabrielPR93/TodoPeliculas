package com.example.todopeliculas.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.data.ActorItemResponse
import com.example.todopeliculas.databinding.ItemActorBinding
import com.squareup.picasso.Picasso

class ActorViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemActorBinding.bind(view)

    fun bind(actorItemResponse: ActorItemResponse){

        //binding.tvActorName.text=actorItemResponse.name

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+actorItemResponse.rutaImagen).into(binding.ImageViewActor)



    }
}