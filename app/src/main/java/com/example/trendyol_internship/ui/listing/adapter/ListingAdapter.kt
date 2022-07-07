package com.example.trendyol_internship.ui.listing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.ui.listing.view.ListingFragmentDirections
import com.example.trendyol_internship.util.downloadFromURL
import com.example.trendyol_internship.util.placeholderProgressBar
import kotlinx.android.synthetic.main.card_view.view.*

class ListingAdapter(val gameList: ArrayList<Game>): RecyclerView.Adapter<ListingAdapter.GameListViewHolder>() {

    // onCreateViewHolder'dan gelen view'ı parametre olarak alıp, RecyclerView.ViewHolder'a bu view'ı paslayacak
    class GameListViewHolder(var view: View) : RecyclerView.ViewHolder(view){

    }

    // GameListViewHolder döndürüyor, grid_cell.xml layout ile adapter'ı birbirine bağlıyor
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_view, parent, false)
        return GameListViewHolder(view)
    }

    // ViewHolder olustu, gameList array'i geldi, viewların değerlerini değiştiriyor
    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.view.name.text = gameList[position].name
        holder.view.imageView.downloadFromURL(gameList[position].backgroundImage, placeholderProgressBar(holder.view.context))

        holder.view.setOnClickListener{
            val action = gameList[position].id?.let { it1 ->
                ListingFragmentDirections.actionListingFragmentToDetailFragment(
                    it1
                )
            }
            if (action != null) {
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
    // kac tane row olusturacagını söylüyoruz
    override fun getItemCount(): Int {
        return gameList.size
    }

    fun updateGameList(newGameList: List<Game>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

}