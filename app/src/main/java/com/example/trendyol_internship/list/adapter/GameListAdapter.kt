package com.example.trendyol_internship.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trendyol_internship.databinding.GameLayoutBinding
import com.example.trendyol_internship.list.model.ListGame

class GameListAdapter: PagingDataAdapter<ListGame, GameListAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(val binding: GameLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            GameLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currChar = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                tvDescription.text = "${currChar?.name}"

                val imageLink = currChar?.background_image
                imageView.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ListGame>() {
            override fun areItemsTheSame(oldItem: ListGame, newItem: ListGame): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListGame, newItem: ListGame): Boolean {
                return oldItem == newItem
            }
        }
    }
}