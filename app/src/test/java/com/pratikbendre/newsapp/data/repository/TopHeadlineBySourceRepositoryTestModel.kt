package com.pratikbendre.newsapp.data.repository

import app.cash.turbine.test
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.model.SourceModel
import com.pratikbendre.newsapp.data.model.TopHeadlinesResponse
import com.pratikbendre.newsapp.utils.AppConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineBySourceRepositoryTestModel {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var topHeadlineBySourceRepository: TopHeadlineBySourceRepository


    @Before
    fun setUp() {
        topHeadlineBySourceRepository = TopHeadlineBySourceRepository(networkService)
    }

    @Test
    fun getTopHeadlineBySource_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val sourceModel = SourceModel(id = "sourceId", name = "sourceName")
            val articleModel = ArticleModel(
                title = "title",
                description = "description",
                url = "url",
                imageurl = "urlToImage",
                sourceModel = sourceModel
            )

            val articleModels = mutableListOf<ArticleModel>()
            articleModels.add(articleModel)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articleModels = articleModels
            )

            doReturn(topHeadlinesResponse).`when`(networkService)
                .getTopHeadlinesBySources(AppConstants.SOURCE_ABC_NEWS)

            topHeadlineBySourceRepository.getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)
                .test {
                    assertEquals(topHeadlinesResponse.articleModels, awaitItem())
                    cancelAndIgnoreRemainingEvents()
                }

            verify(networkService, times(1)).getTopHeadlinesBySources(AppConstants.SOURCE_ABC_NEWS)
        }
    }


    @Test
    fun getTopHeadlineBySource_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {

            val errorMessage = "Error"

            doThrow(RuntimeException(errorMessage)).`when`(networkService)
                .getTopHeadlinesBySources(AppConstants.SOURCE_ABC_NEWS)


            topHeadlineBySourceRepository.getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)
                .test {
                    assertEquals(errorMessage, awaitError().message)
                    cancelAndIgnoreRemainingEvents()
                }

            verify(networkService, times(1)).getTopHeadlinesBySources(AppConstants.SOURCE_ABC_NEWS)
        }
    }


    @Test
    fun getTopHeadlinesByLanguage_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val sourceModel = SourceModel(id = "sourceId", name = "sourceName")
            val articleModel = ArticleModel(
                title = "title",
                description = "description",
                url = "url",
                imageurl = "urlToImage",
                sourceModel = sourceModel
            )

            val articleModels = mutableListOf<ArticleModel>()
            articleModels.add(articleModel)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articleModels = articleModels
            )

            doReturn(topHeadlinesResponse).`when`(networkService)
                .getTopHeadlinesByLanguage(AppConstants.LANGUAGE_EN)

            topHeadlineBySourceRepository.getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)
                .test {
                    assertEquals(topHeadlinesResponse.articleModels, awaitItem())
                    cancelAndIgnoreRemainingEvents()
                }

            verify(networkService, times(1)).getTopHeadlinesByLanguage(AppConstants.LANGUAGE_EN)
        }
    }


    @Test
    fun getTopHeadlinesByLanguage_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {

            val errorMessage = "Error"

            doThrow(RuntimeException(errorMessage)).`when`(networkService)
                .getTopHeadlinesByLanguage(AppConstants.LANGUAGE_EN)


            topHeadlineBySourceRepository.getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)
                .test {
                    assertEquals(errorMessage, awaitError().message)
                    cancelAndIgnoreRemainingEvents()
                }

            verify(networkService, times(1)).getTopHeadlinesByLanguage(AppConstants.LANGUAGE_EN)
        }
    }
}