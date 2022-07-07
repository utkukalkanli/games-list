package com.example.trendyol_internship.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.util.downloadFromURL
import com.example.trendyol_internship.util.placeholderProgressBar
import kotlinx.android.synthetic.main.card_view_game.view.*


class DetailAdapter(val gameDetail: GameDetail): RecyclerView.Adapter<DetailAdapter.GameDetailViewHolder>(){
    class GameDetailViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fragment_detail, parent, false)
        return GameDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameDetailViewHolder, position: Int) {
        holder.view.name.text = gameDetail.name
        //holder.view.imageView.downloadFromURL(gameDetail.backgroundImage, placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return 1
    }

}