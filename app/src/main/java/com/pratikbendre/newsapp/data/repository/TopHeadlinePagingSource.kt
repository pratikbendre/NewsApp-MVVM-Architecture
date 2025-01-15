package com.pratikbendre.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.utils.AppConstants.COUNTRY_US
import com.pratikbendre.newsapp.utils.AppConstants.INITIAL_PAGE
import com.pratikbendre.newsapp.utils.AppConstants.PAGE_SIZE

class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, ArticleModel>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = networkService.getTopHeadlinesByPagination(
                country = COUNTRY_US, page = page, pageSize = PAGE_SIZE
            )
            val filteredresponse = response.articleModels.filter { articleModel ->
                articleModel.title != "[Removed]"
            }
            LoadResult.Page(
                data = filteredresponse,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.articleModels.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}