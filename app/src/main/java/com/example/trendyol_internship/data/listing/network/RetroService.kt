package com.example.trendyol_internship.data.listing.network

import com.example.trendyol_internship.data.listing.model.ApiResponse
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET(Constants.API_KEY)
    suspend fun getGamesFromAPI(
        @Query("page") query: Int
    ): ApiResponse
}