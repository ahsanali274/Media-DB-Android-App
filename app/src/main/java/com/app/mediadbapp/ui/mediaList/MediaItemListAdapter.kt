package com.app.mediadbapp.ui.mediaList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.mediadbapp.callback.ItemClickListener
import com.app.mediadbapp.data.models.Movie
import com.app.mediadbapp.databinding.ItemMovieCardBinding

class MediaItemLitAdapterAdapter(private val listener: ItemClickListener<Movie>) :
    ListAdapter<Movie, MediaItemLitAdapterAdapter.MovieItemViewHolder>(
        movieItemDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    class MovieItemViewHolder(val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, listener: ItemClickListener<Movie>) {
            binding.run {

            }
        }
    }
}

val movieItemDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id
}