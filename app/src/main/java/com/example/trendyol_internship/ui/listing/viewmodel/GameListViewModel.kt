package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.trendyol_internship.list.source.api.ApiService
import com.example.trendyol_internship.list.source.paging.GameListPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameListViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {
//constructor(private val GetPaginatedGameListUseCase) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 1)) {
        GameListPagingSource(apiService)
        // GIVE USE CASE
    }.flow.cachedIn(viewModelScope)
}