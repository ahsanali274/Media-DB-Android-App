package com.app.mediadbapp.ui.mediaList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.mediadbapp.callback.ItemClickListener
import com.app.mediadbapp.data.models.Movie
import com.app.mediadbapp.databinding.ItemMediaCategoryBinding

class MediaCategoryListAdapterAdapter(private val itemListener: ItemClickListener<Movie>) :
    ListAdapter<Pair<String, List<Movie>>, MediaCategoryListAdapterAdapter.MovieCategoryViewHolder>(
        movieCategoryDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        return MovieCategoryViewHolder(
            ItemMediaCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemListener
        )
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MovieCategoryViewHolder(
        private val binding: ItemMediaCategoryBinding,
        listener: ItemClickListener<Movie>
    ) : RecyclerView.ViewHolder(binding.root) {

        private val itemAdapter = MediaItemLitAdapterAdapter(listener)

        init {
            binding.run {
                rvMediaItems.adapter = itemAdapter
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rvMediaItems)
            }
        }

        fun bind(item: Pair<String, List<Movie>>) {
            binding.tvCategoryName.text = item.first
            itemAdapter.submitList(item.second)
        }
    }
}

val movieCategoryDiffCallback = object : DiffUtil.ItemCallback<Pair<String, List<Movie>>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, List<Movie>>,
        newItem: Pair<String, List<Movie>>
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Pair<String, List<Movie>>,
        newItem: Pair<String, List<Movie>>
    ): Boolean =
        oldItem.first == newItem.first
}