package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.trendyol_internship.data.listing.repository.ListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(repository: ListingRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    /** we swap the value of games when the currentQuery changes, switchMap method takes lambda parameter
     *  that will be executed whenever the value of currentQuery livedata changes, when this happens we get passed
     *  a parameter that contains a new value of current query in form of a string
     *  also, we need to add .cachedIn otherwise we get crashed when we rotate the device because we cant load from the same paging data twice
     */
    val games = currentQuery.switchMap { queryString ->
        repository.getListDataFromAPIWithLiveData(queryString).cachedIn(viewModelScope)
    }

    fun searchGames(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = ""

    }
}