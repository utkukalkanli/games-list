package com.example.trendyol_internship.ui.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.databinding.GridCellBinding
import com.example.trendyol_internship.ui.listing.view.ListingFragmentDirections


class ListingAdapter :
    PagingDataAdapter<Game, ListingAdapter.GridCellViewHolder>(DiffUtilCallBack()) {

    class GridCellViewHolder(val binding: GridCellBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: GridCellViewHolder, position: Int) {

        val game = getItem(position)
        if (game != null) {
            holder.binding.gameName.text = game.name
            Glide.with(holder.binding.imageView).load(game.background_image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_baseline_search_24)
                .into(holder.binding.imageView)

            // set on click listener for card view
            holder.binding.cardView.setOnClickListener {
                println("Game ID: " + game.id + "  Game Name: " + game.name)
                val action =
                    ListingFragmentDirections.actionListingFragmentToDetailFragment(game.id!!)
                Navigation.findNavController(it).navigate(action)
            }
        } else {
            //Toast.makeText(this@ListingAdapter, "Can not load data...",Toast.LENGTH_SHORT).show()
            println("Game=Null on ListingAdapter !!!")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridCellViewHolder {
        return GridCellViewHolder(
            GridCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // object that knows how to compare changes if items are same adapter wont render
    class DiffUtilCallBack : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name && oldItem.id == newItem.id && oldItem.background_image == newItem.background_image
        }
    }
}