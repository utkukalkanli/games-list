package com.example.trendyol_internship.list.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendyol_internship.list.model.ListGame
import com.example.trendyol_internship.list.source.api.ApiService

class GameListPagingSource (private val apiService: ApiService) : PagingSource<Int, ListGame>() {
    override fun getRefreshKey(state: PagingState<Int, ListGame>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, ListGame> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllGames(currentPage)
            val responseData = mutableListOf<ListGame>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}