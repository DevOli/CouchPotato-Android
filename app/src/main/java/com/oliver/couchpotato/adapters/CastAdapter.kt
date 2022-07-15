package com.oliver.couchpotato.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oliver.couchpotato.databinding.CastListItemBinding
import com.oliver.couchpotato.db.entities.Cast
import com.oliver.couchpotato.db.entities.Movie
import com.oliver.couchpotato.helper.CAST_IMAGE_BASE_URL

class CastAdapter: ListAdapter<Cast, CastAdapter.CastViewHolder>(POSTS_COMPARATOR) {

    class CastViewHolder(val binding: CastListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): CastViewHolder {
                val binding = CastListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
                return CastViewHolder(binding)
            }
        }
    }

    companion object {
        private val POSTS_COMPARATOR = object : DiffUtil.ItemCallback<Cast>(){
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.personCard.actorName.text = item.name
        holder.binding.personCard.cardImage.load(CAST_IMAGE_BASE_URL + item.profilePath)
        holder.binding.personCard.actorChar.text = item.character
    }
}