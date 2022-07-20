package com.example.trendyol_internship.ui.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.databinding.GridCellBinding


class ListingAdapter : PagingDataAdapter<Game, ListingAdapter.GridCellViewHolder>(DiffUtilCallBack()) {

    class GridCellViewHolder(val binding: GridCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: GridCellViewHolder, position: Int) {
        val game = getItem(position)!!
        holder.binding.gameName.text = game.name
        Glide.with(holder.binding.imageView).load(game.background_image).into(holder.binding.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridCellViewHolder {
        return GridCellViewHolder(GridCellBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name && oldItem.id == newItem.id && oldItem.background_image == newItem.background_image
        }
    }

}