package com.example.trendyol_internship.data.network

import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("games" + Constants.API_KEY)
    suspend fun getGamesFromAPI(
        @Query("page") query: Int,
    ): ApiResponse

    @GET("games" + Constants.API_KEY)
    suspend fun getSearchResultFromAPI(
        @Query("page") query: Int,
        @Query("search") search: String
    ): ApiResponse

    @GET("games/{GAME_ID}" + Constants.API_KEY)
    suspend fun getGameDetailFromAPI(
        @Path("GAME_ID") game_id: Int
    ): GameDetail
}