package com.example.trendyol_internship.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_internship.data.detail.model.GameDetail

class DetailViewModel : ViewModel(){

    val gameDetailLiveData = MutableLiveData<GameDetail>()

    fun getDataFromRoom(){
        val gameDetail = GameDetail(7,"Standpoint",82,backgroundImage = null, descriptionRaw = "Labyrinth",releaseDate = null,genres = null, playTime = 83,publishers = null, redditURL = null, websiteURL = "http://unrulyattractions.com/standpoint")
        gameDetailLiveData.value = gameDetail
    }
}