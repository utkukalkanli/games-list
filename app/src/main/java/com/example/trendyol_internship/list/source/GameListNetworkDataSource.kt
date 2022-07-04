package com.example.trendyol_internship.list.source

import com.example.trendyol_internship.list.model.ApiResponse
import com.example.trendyol_internship.list.source.api.ApiService
import com.example.trendyol_internship.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
class GameListNetworkDataSource(
    private val gameListApi: GameListApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * Fetches the games network and returns the result.
     * This executes on an IO-optimized thread pool, the function is main-safe.
     */
    suspend fun fetchLatestNews(): Response<ApiResponse> =
    // Move the execution to an IO-optimized thread since the ApiService
        // doesn't support coroutines and makes synchronous requests.
        withContext(ioDispatcher) {
            gameListApi.getAllGames()
        }
}


interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllGames(
        @Query("page") page: Int
    ): Response<ApiResponse>
}
 */