package com.example.trendyol_internship.data.listing.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.listing.network.RetroService

class GamePagingSource(val apiService: RetroService):PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // gets called again and again when we refresh pages
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getGamesFromAPI(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}