package com.example.trendyol_internship.list.repository

import androidx.paging.PagingSource
import com.example.trendyol_internship.list.model.ListGame
import com.example.trendyol_internship.list.source.paging.GameListPagingSource

/**

// GameListRepository is consumed from other layers of the hierarchy.
class GameListRepository(
    private val gameListApiDataSource: GameListPagingSource
) {
    suspend fun getPaginatedGamesList(): PagingSource.LoadResult<Int, ListGame> =
        gameListPagedApiDataSource.load()
}

 */