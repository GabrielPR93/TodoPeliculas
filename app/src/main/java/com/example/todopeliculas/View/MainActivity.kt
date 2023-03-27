package com.example.todopeliculas.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.example.todopeliculas.R
import com.example.todopeliculas.View.SearchActivity.Companion.TITULO_CONSULTA
import com.example.todopeliculas.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.home-> {startActivity(Intent(this,MainActivity::class.java)) }

            R.id.search -> {startActivity(Intent(this,SearchActivity::class.java))}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigateToSearch(titulo:String){
        val intent= Intent(this,SearchActivity::class.java)
        intent.putExtra(TITULO_CONSULTA,titulo)
        startActivity(intent)
    }

}