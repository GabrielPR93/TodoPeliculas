package com.example.todopeliculas.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todopeliculas.R
import com.example.todopeliculas.data.ActorDetailDataResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.data.network.Retrofit.Companion.getRetrofit
import com.example.todopeliculas.databinding.ActivityDetailActorBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

class DetailActorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailActorBinding
    private lateinit var retrofit: Retrofit

    companion object {
        const val ID_ACTOR = "id_actor"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit=getRetrofit()

        val id: Int = intent.getIntExtra(ID_ACTOR,0)
        getDetailActor(id)
        Log.i("G","---> "+id)
    }

    private fun getDetailActor(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val actorDetail = retrofit.create(ApiService::class.java).getDetailActor(id)

            if(actorDetail.body()!=null){
                runOnUiThread{
                    createUI(actorDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(actor: ActorDetailDataResponse) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+actor.path).into(binding.ImageViewActor)
        binding.textViewName.text=actor.name
        binding.idDepartment.text=actor.department
        binding.idFechaNacimiento.text=StringBuilder(actor.birthday).append(" - ").append(actor.deathday?:"")
        binding.idLugarNacimiento.text=actor.place_birth
        binding.idBiografia.text=actor.biography


    }


}