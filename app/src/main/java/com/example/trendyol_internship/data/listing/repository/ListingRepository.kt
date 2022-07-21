package com.example.trendyol_internship.data.listing.repository

import androidx.paging.*
import com.example.trendyol_internship.data.listing.paging.GamePagingSource
import com.example.trendyol_internship.data.network.NetworkService
import com.example.trendyol_internship.data.network.RetroInstance

class ListingRepository {

    private var retroService: NetworkService =
        RetroInstance.getRetroInstance().create(NetworkService::class.java)

    fun getListDataFromAPIWithLiveData() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { GamePagingSource(retroService) }
    ).liveData

}