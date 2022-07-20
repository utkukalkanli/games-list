package com.example.trendyol_internship.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.listing.paging.GamePagingSource
import com.example.trendyol_internship.data.network.NetworkService
import com.example.trendyol_internship.data.network.RetroInstance
import com.example.trendyol_internship.data.search.SearchPagingSource
import kotlinx.coroutines.flow.Flow

class SearchViewModel() : ViewModel() {

    var retroService: NetworkService = RetroInstance.getRetroInstance().create(NetworkService::class.java)

    fun getSearchResultFromAPI(): Flow<PagingData<Game>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { SearchPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }

}