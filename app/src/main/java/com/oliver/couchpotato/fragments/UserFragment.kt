package com.oliver.couchpotato.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.oliver.couchpotato.R
import com.oliver.couchpotato.adapters.PopularMoviesAdapter
import com.oliver.couchpotato.databinding.FragmentHomeBinding
import com.oliver.couchpotato.databinding.FragmentUserBinding
import com.oliver.couchpotato.helper.PROFILE_URL
import com.oliver.couchpotato.viewmodels.MainViewModel
import com.oliver.couchpotato.viewmodels.UserViewModel

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

        viewModel.getUser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.user.observe(this) {
            binding.userName.text = it.name
            binding.userEmail.text = it.username
            binding.userImage.load(PROFILE_URL + it.avatar?.tmdb?.avatarPath)
        }
        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}