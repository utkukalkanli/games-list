package com.example.trendyol_internship.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.detail.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private val gameDetail: MutableLiveData<GameDetail> = MutableLiveData()

    fun getGameDetail(id: Int): GameDetail? {
        viewModelScope.launch {
            val response = repository.getGameDetailAPIResponse(id)
            println("VIEWMODEL ${response.name}")
            gameDetail.value = response
        }
        return gameDetail.value
    }

}