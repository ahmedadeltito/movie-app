package com.talentsarena.ui.movie.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.talentsarena.ui.movie.BuildConfig
import com.talentsarena.ui.movie.databinding.MovieListItemBinding
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Adapter for showing [MovieUiModel] in a list.
 */
class MovieListAdapter :
    ListAdapter<MovieUiModel, MovieListAdapter.ViewHolder>(MovieItemDiffCallback()) {

    var onMovieClickListener: ((movieId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder = ViewHolder(
        binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(movie = getItem(position), onMovieClickListener = onMovieClickListener)
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUiModel, onMovieClickListener: ((movieId: Int) -> Unit)?) {
            binding.movieTitleTv.text = movie.title
            binding.movieDescTv.text = movie.overview
            binding.movieImageIv.load(uri = BuildConfig.IMAGE_BASE_URL + movie.posterPath)
            itemView.setOnClickListener {
                onMovieClickListener?.invoke(movie.id)
            }
        }
    }
}