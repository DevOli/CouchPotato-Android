package com.oliver.couchpotato.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oliver.couchpotato.MovieApp
import com.oliver.couchpotato.R
import com.oliver.couchpotato.adapters.MovieListener
import com.oliver.couchpotato.adapters.NewMoviesAdapter
import com.oliver.couchpotato.adapters.PopularMoviesAdapter
import com.oliver.couchpotato.databinding.FragmentHomeBinding
import com.oliver.couchpotato.viewmodels.MainViewModel

class HomeFragment : Fragment(), MovieListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPopularMoviesRecycler()
        setUpNewMoviesRecycler()
        setObservers()

        //viewModel.deleteTable()
        viewModel.getPopularMovies()
        viewModel.getNewMovies()

    }

    private fun setUpPopularMoviesRecycler() {
        binding.popRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.popRecycler.setHasFixedSize(true)
        binding.popRecycler.adapter = PopularMoviesAdapter(this)
    }

    private fun setUpNewMoviesRecycler() {
        binding.releaseRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.releaseRecycler.setHasFixedSize(true)
        binding.releaseRecycler.adapter = NewMoviesAdapter(this)
    }

    private fun setObservers() {
        viewModel.popularMovieList.observe(this) {
            (binding.popRecycler.adapter as PopularMoviesAdapter).submitList(it)
        }
        viewModel.isLoadingPop.observe(this) {
            binding.popProgress.visibility = if (it) View.VISIBLE else View.GONE
            binding.popRecycler.visibility = if (it) View.INVISIBLE else View.VISIBLE
        }

        viewModel.newMovieList.observe(this) {
            (binding.releaseRecycler.adapter as NewMoviesAdapter).submitList(it)
        }

        viewModel.isLoadingNew.observe(this) {
            binding.releaseProgress.visibility = if (it) View.VISIBLE else View.GONE
            binding.releaseRecycler.visibility = if (it) View.INVISIBLE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun didMovieSelected(movieId: Long) {
        val bundleToD = Bundle()
        bundleToD.putLong(MovieDetailsFragment.MOVIE_ID, movieId)
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundleToD)
    }
}