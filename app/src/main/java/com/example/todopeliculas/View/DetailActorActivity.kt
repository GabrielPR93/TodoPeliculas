package com.example.todopeliculas.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todopeliculas.R
import com.example.todopeliculas.databinding.ActivityDetailActorBinding

class DetailActorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailActorBinding

    companion object {
        const val ID_ACTOR = "id_actor"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActorBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}