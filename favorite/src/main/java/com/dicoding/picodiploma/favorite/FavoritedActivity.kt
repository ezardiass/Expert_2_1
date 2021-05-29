package com.dicoding.picodiploma.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.architecturecomponentsub1.di.FavoriteModule.viewModelFavModule
import com.dicoding.picodiploma.architecturecomponentsub1.view.MovieDetailsActivity
import com.dicoding.picodiploma.favorite.viewmodel.FavoritedMoviesViewModel
import com.dicoding.picodiploma.core.adapter.MoviesAdapter
import com.dicoding.picodiploma.favorite.databinding.ActivityFavoritedBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritedActivity : AppCompatActivity() {

    private val viewModel: FavoritedMoviesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFavoritedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(viewModelFavModule)
        val adapter = MoviesAdapter()
        adapter.onItemClick = { selectedData ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.MOVIE, selectedData)
            startActivity(intent)
        }
        binding.pbMovieFav.visibility = View.VISIBLE
        viewModel.getFavoritedMovies.observe(this, { movies ->
            if (movies != null) {
                binding.pbMovieFav.visibility = View.GONE
                adapter.setData(movies)
            }

        }
        )

        binding.rvMovieFav.layoutManager = LinearLayoutManager(this)
        binding.rvMovieFav.setHasFixedSize(true)
        binding.rvMovieFav.adapter = adapter

    supportActionBar?.title="Favorites"
}}
