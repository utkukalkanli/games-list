package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.network.RetroInstance
import com.example.trendyol_internship.data.network.NetworkService
import com.example.trendyol_internship.data.listing.paging.GamePagingSource
import kotlinx.coroutines.flow.Flow

class ListingViewModel: ViewModel() {

    var retroService: NetworkService = RetroInstance.getRetroInstance().create(NetworkService::class.java)

    fun getListDataFromAPI(): Flow<PagingData<Game>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200), pagingSourceFactory = {GamePagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }



}