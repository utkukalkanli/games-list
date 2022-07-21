package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.trendyol_internship.data.listing.repository.ListingRepository

class ListingViewModel : ViewModel() {

    private val repository = ListingRepository()

    val paginatedGameData = repository.getListDataFromAPIWithLiveData().cachedIn(viewModelScope)


}