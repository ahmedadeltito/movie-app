package com.talentsarena.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.talentsarena.core.thread.DefaultTaskExecutor
import com.talentsarena.ui.movie.coordinator.MovieCoordinator
import com.talentsarena.ui.movie.coordinator.MovieNavigator
import com.talentsarena.ui.movie.model.MovieUiModel
import com.talentsarena.ui.movie.list.di.MovieListFragmentServiceLocator
import com.talentsarena.ui.movie.list.viewmodel.MovieListViewModel
import com.talentsarena.ui.movie.list.viewmodel.MovieListViewModelFactory
import com.talentsarena.ui.movie.databinding.FragmentMovieListBinding

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

    private val movieCoordinator: MovieCoordinator = MovieListFragmentServiceLocator.getCoordinator(
        navigator = MovieNavigator()
    )

    private val movieListViewModel: MovieListViewModel by viewModels {
        val movieUseCase = MovieListFragmentServiceLocator.getMovieUseCase(
            apiKey = "fc47660226072874be57974ff797a0cd",
            context = this.requireContext()
        )
        MovieListViewModelFactory(
            movieUseCase = movieUseCase,
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
        binding?.movieListView?.let {
            movieListViewModel.movieList.observe(viewLifecycleOwner) { movies ->
                movieList += movies
                it.showLoading(isLoading = false, isFirstPage = isFirstPage)
                it.showMovies(movies = movieList)
            }
            movieListViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                it.showLoading(isLoading = false, isFirstPage = isFirstPage)
                it.showErrorMessage(message = message)
            }
            it.showLoading(isLoading = true, isFirstPage = true)
            movieListViewModel.getMovies(pageNumber = 1)
            it.addCallbacks(
                getMovies = { pageNumber ->
                    isFirstPage = pageNumber == 1
                    if (isFirstPage) {
                        movieList.clear()
                    }
                    it.showLoading(isLoading = true, isFirstPage = isFirstPage)
                    movieListViewModel.getMovies(pageNumber = pageNumber)
                },
                onMovieClickListener = { movieId ->
                    if (activity != null && activity is AppCompatActivity) {
                        movieCoordinator.startMovieDetailsFragment(
                            activity = activity as AppCompatActivity,
                            movieId = movieId
                        )
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}