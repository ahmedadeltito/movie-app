package com.talentsarena.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.talentsarena.core.extensions.gone
import com.talentsarena.core.extensions.visible
import com.talentsarena.core.extensions.visibleOrGone
import com.talentsarena.core.thread.DefaultTaskExecutor
import com.talentsarena.core.widget.EndlessRecyclerViewScrollListener
import com.talentsarena.movieapp.adapter.MovieListAdapter
import com.talentsarena.movieapp.databinding.FragmentMovieListBinding
import com.talentsarena.movieapp.model.MovieUiModel
import com.talentsarena.movieapp.viewmodel.MovieListViewModel
import com.talentsarena.movieapp.viewmodel.MovieListViewModelFactory

/**
 * Fragment that shows [MovieUiModel] in a list appearance.
 */
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance(args: Bundle? = null): MovieListFragment = MovieListFragment().apply {
            arguments = args
        }
    }

    private var binding: FragmentMovieListBinding? = null

    private val movieListAdapter = MovieListAdapter()

    private val movieListViewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(
            apiKey = "fc47660226072874be57974ff797a0cd",
            context = requireContext(),
            executor = DefaultTaskExecutor()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieList: MutableList<MovieUiModel> = mutableListOf()
        var isFirstPage = true
        binding?.apply {

            movieListView.apply {
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)
                adapter = movieListAdapter
            }

            movieListSrl.setOnRefreshListener {
                movieListViewModel.getMovies(pageNumber = 1)
            }

            movieListView.addOnScrollListener(
                object : EndlessRecyclerViewScrollListener(movieListView.layoutManager as LinearLayoutManager) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int) {
                        isFirstPage = page == 1
                        if (isFirstPage) {
                            movieList.clear()
                        }
                        showLoading(isLoading = true, isFirstPage = isFirstPage)
                        movieListViewModel.getMovies(pageNumber = page)
                    }
                }
            )

            movieListAdapter.onMovieClickListener = { movieId ->
                Toast.makeText(requireContext(), "Movie with id $movieId is clicked", Toast.LENGTH_SHORT).show()
            }

            movieListViewModel.movieList.observe(viewLifecycleOwner) { movies ->
                movieList += movies
                showLoading(isLoading = false, isFirstPage = isFirstPage)
                showMovies(movies = movieList)
            }
            movieListViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                showLoading(isLoading = false, isFirstPage = isFirstPage)
                showErrorMessage(message = message)
            }

            showLoading(isLoading = true, isFirstPage = true)
            movieListViewModel.getMovies(pageNumber = 1)
        }
    }

    private fun showLoading(isLoading: Boolean, isFirstPage: Boolean) {
        binding?.apply {
            if (isFirstPage) {
                movieListSrl.post {
                    movieListSrl.isRefreshing = isLoading
                }
            } else {
                loadMorePb.post {
                    loadMorePb.visibleOrGone(show = isLoading)
                }
            }
        }
    }

    private fun showMovies(movies: List<MovieUiModel>) {
        binding?.apply {
            loadMorePb.gone()
            emptyListTv.gone()
            movieListView.visible()

            movieListAdapter.submitList(movies)
        }
    }

    private fun showErrorMessage(message: String?) {
        binding?.apply {
            loadMorePb.gone()
            emptyListTv.visible()
            movieListView.gone()

            emptyListTv.text = message
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}