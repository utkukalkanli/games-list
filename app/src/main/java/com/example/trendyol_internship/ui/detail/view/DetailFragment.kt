package com.example.trendyol_internship.ui.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trendyol_internship.R
import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.ui.detail.adapter.DetailAdapter
import com.example.trendyol_internship.ui.detail.viewmodel.DetailViewModel
import com.example.trendyol_internship.util.downloadFromURL
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private lateinit var viewModel : DetailViewModel
    //private val detailAdaoter : DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // fragmentlar arası data transferi bu sekilde yapılabilir
        arguments?.let {
            val gameID = DetailFragmentArgs.fromBundle(it).id
            println("GAMEID: " + gameID)
            viewModel.getGameDetail(gameID)
        }
        println(viewModel.gameDetail)
        // initialize layout manager
        // initialize adapter

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.gameDetail.observe(viewLifecycleOwner, Observer{ gameDetail->
            gameDetail?.let {
                gameName.text = "Name: " + gameDetail.name
                metacritic.text = "Score: " + gameDetail.metaCritic.toString()
                gameDescription.text = "Description: " + gameDetail.descriptionRaw
                releaseDate.text = "Released: " + gameDetail.releaseDate.toString()
                genres.text = "Detail: " + gameDetail.genres.toString()
                playTime.text = "Playtime: " + gameDetail.playTime.toString()
                publishers.text = "Publishers: " + gameDetail.publishers.toString()
                redditURL.text = "Reddit: " + gameDetail.redditURL
                websiteURL.text = "Website: " + gameDetail.websiteURL
                // gameDetailImage.imageView.downloadFromURL(gameDetail.backgroundImage)
            }
        })
    }
}