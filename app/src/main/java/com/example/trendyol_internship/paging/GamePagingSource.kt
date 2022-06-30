package com.example.trendyol_internship.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendyol_internship.api.ApiService
import com.example.trendyol_internship.model.Game

class GamePagingSource (private val apiService: ApiService) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Game> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllGames(currentPage)
            val responseData = mutableListOf<Game>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}