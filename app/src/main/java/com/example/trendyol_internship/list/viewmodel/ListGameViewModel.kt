package com.example.trendyol_internship.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.trendyol_internship.list.api.ApiService
import com.example.trendyol_internship.list.paging.GamePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListGameViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) { GamePagingSource(apiService)

    }.flow.cachedIn(viewModelScope)

}