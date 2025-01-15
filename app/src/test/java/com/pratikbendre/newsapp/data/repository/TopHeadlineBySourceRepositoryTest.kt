package com.pratikbendre.newsapp.data.repository

import app.cash.turbine.test
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.model.Source
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
class TopHeadlineBySourceRepositoryTest {

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
            val source = Source(id = "sourceId", name = "sourceName")
            val article = Article(
                title = "title",
                description = "description",
                url = "url",
                imageurl = "urlToImage",
                source = source
            )

            val articles = mutableListOf<Article>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articles = articles
            )

            doReturn(topHeadlinesResponse).`when`(networkService)
                .getTopHeadlinesBySources(AppConstants.SOURCE_ABC_NEWS)

            topHeadlineBySourceRepository.getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)
                .test {
                    assertEquals(topHeadlinesResponse.articles, awaitItem())
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
            val source = Source(id = "sourceId", name = "sourceName")
            val article = Article(
                title = "title",
                description = "description",
                url = "url",
                imageurl = "urlToImage",
                source = source
            )

            val articles = mutableListOf<Article>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articles = articles
            )

            doReturn(topHeadlinesResponse).`when`(networkService)
                .getTopHeadlinesByLanguage(AppConstants.LANGUAGE_EN)

            topHeadlineBySourceRepository.getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)
                .test {
                    assertEquals(topHeadlinesResponse.articles, awaitItem())
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