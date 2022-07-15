package com.oliver.couchpotato.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliver.couchpotato.databinding.MovieListItemBinding

class MoviesViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val binding =
                MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MoviesViewHolder(binding)
        }
    }
}