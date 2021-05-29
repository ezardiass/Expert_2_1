package com.dicoding.picodiploma.architecturecomponentsub1.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.core.domain.model.Movie
import com.dicoding.picodiploma.architecturecomponentsub1.R
import com.dicoding.picodiploma.architecturecomponentsub1.databinding.ActivityMovieDetailBinding
import com.dicoding.picodiploma.architecturecomponentsub1.viewmodel.DetailMoviesViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    companion object{
        const val MOVIE="movie"
    }
    private lateinit var binding : ActivityMovieDetailBinding
    private val viewModel: DetailMoviesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding= ActivityMovieDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.title="Movie Details"
        val movie = intent.getParcelableExtra<Movie>(MOVIE)
        if (movie != null) {
            binding.tvTitle.text = movie.title
            binding.tvDuration.text = movie.duration
            binding.tvDate.text = movie.date
            binding.tvDesc.text = movie.description

            Picasso.get()
                .load("https://image.tmdb.org/t/p/original"+movie.image)
                .into(binding.poster)
        }

        binding.button.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Go watch $title now at Movies and Tvs App "  )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        if (movie!=null) {
            var statusFavorite = movie.favorited
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovies(movie,statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unfavorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite))
        }
    }
}