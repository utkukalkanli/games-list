package com.example.trendyol_internship.data.listing.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.network.ApiResponse
import com.example.trendyol_internship.data.network.NetworkService

private const val STARTING_PAGE_INDEX = 1

class GamePagingSource(
    private val apiService: NetworkService,
    private val query : String
    ) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // gets called again and again when we refresh pages
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        // we use elvis operator since params.key is null at the beginning
        val page = params.key
            ?: STARTING_PAGE_INDEX // ilk yükleme esnasında key null olacaktır, o zaman STARTING_PAGE_INDEX kullanıyoruz
        return try {
            var response = ApiResponse(arrayListOf())
            if (query.isEmpty()){
                response = apiService.getGamesFromAPI(page)
            }
            else if (query.isNotEmpty()){
                response = apiService.getSearchResultFromAPI(page,query)
            }
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}