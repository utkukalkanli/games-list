package com.example.trendyol_internship.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_internship.data.listing.Game

class DetailViewModel : ViewModel(){

    val gameDetailLiveData = MutableLiveData<Game>()

    fun getDataFromRoom(){
        val game = Game(6,"Flatout 2", "Car race", "www.ss.com",78)
        gameDetailLiveData.value = game
    }
}