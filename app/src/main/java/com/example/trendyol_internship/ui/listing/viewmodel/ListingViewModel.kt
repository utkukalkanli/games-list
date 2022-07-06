package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_internship.data.listing.Game

class ListingViewModel: ViewModel() {
    // mutable = değiştirilebilir data
    val gameList = MutableLiveData<List<Game>>()
    val gameListError = MutableLiveData<Boolean>()
    val gameListLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val game = Game(1,"Pay Day", "1-some game", "www.ss.com", 93)
        val game2 = Game(2,"Call of Duty", "2-some game", "www.ss.com", 56)
        val game3 = Game(3,"Fifa 07", "3-some game", "www.ss.com", 88)

        val games = arrayListOf<Game>(game,game2,game3)

        gameList.value = games
        gameListError.value = false
        gameListLoading.value = false
    }
}