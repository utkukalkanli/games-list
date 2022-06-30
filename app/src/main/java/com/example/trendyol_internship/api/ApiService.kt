package com.example.trendyol_internship.api

import com.example.trendyol_internship.model.ResponseApi
import com.example.trendyol_internship.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllGames(
        // @Query("count") size:Int,
        @Query("page") page: Int

    ): Response<ResponseApi>
}