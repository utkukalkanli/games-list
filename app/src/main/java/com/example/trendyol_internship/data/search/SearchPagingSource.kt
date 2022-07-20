package com.example.trendyol_internship.data.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.network.NetworkService

class SearchPagingSource(private val apiService: NetworkService): PagingSource<Int, Game>() {

    private lateinit var keyword: String

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    // gets called again and again when we refresh pages
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val page = params.key ?: STARTING_PAGE_INDEX // ilk yükleme esnasında key null olacaktır, o zaman STARTING_PAGE_INDEX kullanıyoruz
        return try {
            val response = apiService.getSearchResultFromAPI(page, keyword)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    fun setSearchKeyword(keyword : String){
        this.keyword = keyword
    }

    fun getSearchKeyword() : String{
        return this.keyword
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}