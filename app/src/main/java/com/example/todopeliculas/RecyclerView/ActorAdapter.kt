package com.example.todopeliculas.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.R
import com.example.todopeliculas.data.ActorItemResponse

//https://api.themoviedb.org/3/movie/299536/credits?api_key=dd25ec6365ddb50099ea71752bb96cce&language=es-Es --> actores

class ActorAdapter(var actorList: List<ActorItemResponse> = emptyList()): RecyclerView.Adapter<ActorViewHolder>(){

    fun updateList(actorList: List<ActorItemResponse>){
        this.actorList=actorList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
       return ActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actor,parent,false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
       holder.bind(actorList[position])

    }

    override fun getItemCount() = actorList.size




}
