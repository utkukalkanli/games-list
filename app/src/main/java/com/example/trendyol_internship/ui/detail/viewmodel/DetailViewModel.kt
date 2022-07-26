package com.example.trendyol_internship.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.detail.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    var gameDetail: MutableLiveData<GameDetail> = MutableLiveData()

    fun updateGameDetail(id: Int) {
        viewModelScope.launch {
            val response = repository.getGameDetailAPIResponse(id)
            gameDetail.value = response
        }
    }
}