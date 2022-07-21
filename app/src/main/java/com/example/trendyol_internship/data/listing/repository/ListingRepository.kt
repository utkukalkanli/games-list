package com.example.trendyol_internship.data.listing.repository

import androidx.paging.*
import com.example.trendyol_internship.data.listing.paging.GamePagingSource
import com.example.trendyol_internship.data.network.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListingRepository @Inject constructor(private val retroService : NetworkService) {

    fun getListDataFromAPIWithLiveData(query : String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { GamePagingSource(retroService, query) }
    ).liveData

}