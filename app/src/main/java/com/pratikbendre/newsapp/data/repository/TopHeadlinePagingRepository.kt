package com.pratikbendre.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.utils.AppConstants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinePagingRepository @Inject constructor(private val networkService: NetworkService) {
    fun geTopHeadlines(): Flow<PagingData<ArticleModel>> {
        return Pager(config = PagingConfig(
            pageSize = PAGE_SIZE
        ),
            pagingSourceFactory = {
                TopHeadlinePagingSource(networkService)
            }).flow
    }
}