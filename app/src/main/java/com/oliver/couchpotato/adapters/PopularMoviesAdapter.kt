package com.oliver.couchpotato.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.oliver.couchpotato.databinding.MovieListItemBinding
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.helper.POSTER_IMAGE_BASE_URL

class PopularMoviesAdapter(private val listener: MovieListener): ListAdapter<Movie, MoviesViewHolder>(POSTS_COMPARATOR) {

    var context: Context? = null

//    class MoviesViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        companion object {
//            fun create(parent: ViewGroup): MoviesViewHolder {
//                val binding =
//                    MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                return MoviesViewHolder(binding)
//            }
//        }
//    }

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

//        val imageLoader = context?.imageLoader
//        val test = POSTER_IMAGE_BASE_URL + item.posterPath
//        val request = ImageRequest.Builder(context!!)
//            .data(POSTER_IMAGE_BASE_URL + item.posterPath)
//            .target { drawable ->
//                holder.binding.movieCard.cardImageBackground.setBackgroundDrawable(drawable)
//            }
//            .build()
//        val disposable = imageLoader?.enqueue(request)

    }
}