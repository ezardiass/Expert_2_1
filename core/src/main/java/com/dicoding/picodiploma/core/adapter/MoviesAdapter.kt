package com.dicoding.picodiploma.core.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.core.R
import com.dicoding.picodiploma.core.databinding.ItemsMoviesBinding
import com.dicoding.picodiploma.core.domain.model.Movie
import com.squareup.picasso.Picasso


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsMoviesBinding.bind(itemView)
        fun bind(data: Movie) {

            with(binding) {
                Picasso.get()
                        .load("https://image.tmdb.org/t/p/original"+data.image)
                        .into(imgPosterM)
                tvItemTitleM.text = data.title
                tvItemDateM.text = data.date
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}

