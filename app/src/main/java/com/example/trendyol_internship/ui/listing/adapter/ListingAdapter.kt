package com.example.trendyol_internship.ui.listing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.ui.listing.view.ListingFragmentDirections
import com.example.trendyol_internship.util.downloadImage
import kotlinx.android.synthetic.main.grid_cell.view.*


class ListingAdapter(): PagingDataAdapter<Game, ListingAdapter.MyViewHolder>(DiffUtilCallBack()), CardViewClickListener {

    override fun onBindViewHolder(holder: ListingAdapter.MyViewHolder, position: Int) {
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
            // downloadImage(view = imageView, url = data.backgroundImage)
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

    override fun onCardViewClicked(view: View) {
        println(view.id)
    }

}