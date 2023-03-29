package com.example.todopeliculas.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todopeliculas.R
import com.example.todopeliculas.RecyclerView.MovieTrendingAdapter
import com.example.todopeliculas.View.SearchActivity.Companion.TITULO_CONSULTA
import com.example.todopeliculas.data.TrendingDataResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.data.network.Retrofit.Companion.getRetrofit
import com.example.todopeliculas.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MovieTrendingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit= getRetrofit()
        initUI()
        getMoviesTrending()
    }

    private fun initUI() {
        binding.searchViewPrincipal.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                navigateToSearch(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter=MovieTrendingAdapter{movieId->navigateToDetail(movieId)}
        binding.reciclerViewTendencias.setHasFixedSize(true)
        binding.reciclerViewTendencias.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.reciclerViewTendencias.adapter=adapter

    }

    private fun getMoviesTrending(){
        CoroutineScope(Dispatchers.IO).launch {

            val moviewTrending = retrofit.create(ApiService::class.java).getTrending()

            if(moviewTrending.isSuccessful){
                val response = moviewTrending.body()
                if(response!=null){
                    runOnUiThread{
                        adapter.updateList(response.moviesTrending)
                    }
                }
            }

        }
    }




    private fun navigateToSearch(titulo:String){
        val intent= Intent(this,SearchActivity::class.java)
        intent.putExtra(TITULO_CONSULTA,titulo)
        startActivity(intent)
    }

    private fun navigateToDetail(id:Int){
        val intent= Intent(this,DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_ID,id)
        startActivity(intent)
    }

}