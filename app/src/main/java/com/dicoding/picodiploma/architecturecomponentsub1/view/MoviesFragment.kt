package com.dicoding.picodiploma.architecturecomponentsub1.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.architecturecomponentsub1.databinding.FragmentMoviesBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.core.adapter.MoviesAdapter
import com.dicoding.picodiploma.architecturecomponentsub1.viewmodel.MoviesViewModel
import com.dicoding.picodiploma.core.data.source.Resource
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val adapter = MoviesAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(activity, MovieDetailsActivity::class.java)
                intent.putExtra(MovieDetailsActivity.MOVIE, selectedData)
                startActivity(intent)
            }
            viewModel.getMovies.observe(viewLifecycleOwner
            ) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding.pb1.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pb1.visibility = View.GONE
                            adapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding.pb1.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = adapter
            }

        }
    }


