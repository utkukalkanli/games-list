package com.example.trendyol_internship.data.detail.repository

import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.network.NetworkService
import com.example.trendyol_internship.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(private val retroService: NetworkService) {

    suspend fun getGameDetailAPIResponse(id: Int): GameDetail {
        return retroService.getGameDetailFromAPI(id)
    }

}