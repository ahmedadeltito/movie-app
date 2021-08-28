package com.talentsarena.ui.movie.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.talentsarena.ui.movie.model.MovieUiModel

/**
 * Callback function for [MovieUiModel] used in the [MovieListAdapter].
 */
class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean = oldItem == newItem
}