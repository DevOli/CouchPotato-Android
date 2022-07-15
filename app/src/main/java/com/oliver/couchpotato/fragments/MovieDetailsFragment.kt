package com.oliver.couchpotato.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.oliver.couchpotato.MovieApp
import com.oliver.couchpotato.R
import com.oliver.couchpotato.adapters.CastAdapter
import com.oliver.couchpotato.adapters.PopularMoviesAdapter
import com.oliver.couchpotato.databinding.FragmentHomeBinding
import com.oliver.couchpotato.databinding.FragmentMovieDetailsBinding
import com.oliver.couchpotato.helper.POSTER_IMAGE_BASE_URL
import com.oliver.couchpotato.viewmodels.MainViewModel

class MovieDetailsFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "movie_id"
    }

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private var movieId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getLong(MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setUpCastRecycler()

        movieId?.also {
            viewModel.getMovieById(it)
            viewModel.getCastbyMovieId(it)
        }
    }

    private fun setObservers() {
        viewModel.castList.observe(this) {
            (binding.castRecycler.adapter as CastAdapter).submitList(it)
        }

        viewModel.movie.observe(this) {
            binding.movieTitle.text = it.title
            binding.movieOverview.text = it.overview
            binding.movieDuration.text = it.runtime?.let { runtime ->
                String.format(getString(R.string.movie_duration), (runtime/60), runtime%60)
            }

            binding.movieImage.load(POSTER_IMAGE_BASE_URL + it.posterPath)
            binding.castText.visibility = View.VISIBLE

        }
    }

    private fun setUpCastRecycler() {
        binding.castRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.castRecycler.setHasFixedSize(true)
        binding.castRecycler.adapter = CastAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}