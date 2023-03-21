package com.example.todopeliculas.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todopeliculas.R
import com.example.todopeliculas.RecyclerView.MovieAdapter
import com.example.todopeliculas.View.DetailMovieActivity.Companion.EXTRA_ID
import com.example.todopeliculas.data.MovieDataResponse
import com.example.todopeliculas.data.network.ApiService
import com.example.todopeliculas.data.network.Retrofit.Companion.getRetrofit
import com.example.todopeliculas.databinding.SearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: SearchBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUI()
        binding.bottomNavigation2.setOnItemSelectedListener { onOptionsItemSelected(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home-> { startActivity(Intent(this,MainActivity::class.java)) }

            R.id.search -> {startActivity(Intent(this,SearchActivity::class.java))}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initUI() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter= MovieAdapter{ movieId -> navigateToDetail(movieId)}
        binding.reciclerViewPrincipal.setHasFixedSize(true)
        binding.reciclerViewPrincipal.layoutManager=GridLayoutManager(this,2)
        binding.reciclerViewPrincipal.adapter=adapter
    }

    private fun searchByName(query: String) {
        binding.ProgresBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getMovie(query)
            if (myResponse.isSuccessful) {
                Log.i("Gabri", "Funciona !!!!")
                val response: MovieDataResponse? = myResponse.body()

                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.Movies)
                        binding.ProgresBar.isVisible = false
                    }

                    Log.i("Gabri", response.toString())
                }

            } else {
                showMessageError()
            }
        }

    }

    private fun showMessageError(){
        Log.i("Gabri", "NO FUNCIONA ;((")
        runOnUiThread {
            binding.ProgresBar.isVisible = false

        }
    }



    private fun navigateToDetail(id:Int){
        val intent= Intent(this,DetailMovieActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }

}