package com.example.trendyol_internship.ui.listing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.listing.model.Game



class ListingAdapter(): PagingDataAdapter<Game, ListingAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: ListingAdapter.MyViewHolder, position: Int) {
        // holder.view.imageView.downloadFromURL(gameDetail.backgroundImage, placeholderProgressBar(holder.view.context))
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.grid_cell, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val tvName: TextView = view.findViewById(R.id.gameName)


        fun bind(data: Game) {
            tvName.text = data.name
            Glide.with(imageView)
                .load(data.background_image)
                .into(imageView)
        }
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