package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.MutableLiveData
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
import com.example.trendyol_internship.ui.listing.adapter.ListingAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListingViewModel: ViewModel() {

    private var retroService: NetworkService = RetroInstance.getRetroInstance().create(NetworkService::class.java)

    //val paginatedGameData = MutableLiveData<Flow<PagingData<Game>>>()
    /**
    init {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            getListDataFromAPI().collectLatest {
                listingAdapter.submitData(it)
            }
        }
    }
    */
    fun getListDataFromAPI(): Flow<PagingData<Game>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200), pagingSourceFactory = {GamePagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }



}