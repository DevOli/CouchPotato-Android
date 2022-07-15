package com.oliver.couchpotato.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.helper.POSTER_IMAGE_BASE_URL

class NewMoviesAdapter(private val listener: MovieListener): ListAdapter<Movie, MoviesViewHolder>(POSTS_COMPARATOR) {

    var context: Context? = null

    companion object {
        private val POSTS_COMPARATOR = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        context = parent.context
        return MoviesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.movieCard.movieName.text = item.title
        holder.binding.movieCard.date.text = item.releaseDate
        holder.binding.movieCard.cardImage.load(POSTER_IMAGE_BASE_URL + item.posterPath)

        holder.binding.movieCard.cardFrame.setOnClickListener {
            listener.didMovieSelected(item.id)
        }
    }
}