package com.pratikbendre.newsapp.data.ui.topheadlinebysource

import app.cash.turbine.test
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.repository.TopHeadlineBySourceRepository
import com.pratikbendre.newsapp.data.utils.TestDispatcherProvider
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceViewModel
import com.pratikbendre.newsapp.utils.AppConstants
import com.pratikbendre.newsapp.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineBySourceViewModelTest {

    @Mock
    private lateinit var topHeadlineBySourceRepository: TopHeadlineBySourceRepository

    private lateinit var dispatcherProvider: DispatcherProvider


    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }


    @Test
    fun fetchNewsBySource_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineBySourceRepository)
                .getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)

            val viewModel =
                TopHeadlineBySourceViewModel(topHeadlineBySourceRepository, dispatcherProvider)

            viewModel.fetchNewsBySource(AppConstants.SOURCE_ABC_NEWS)

            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<Article>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(
                topHeadlineBySourceRepository,
                times(1)
            ).getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)
        }
    }

    @Test
    fun fetchNewsBySource_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errormessage = "Error"

            doReturn(flow<List<Article>> {
                throw IllegalStateException(errormessage)
            })
                .`when`(topHeadlineBySourceRepository)
                .getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)

            val viewModel =
                TopHeadlineBySourceViewModel(topHeadlineBySourceRepository, dispatcherProvider)

            viewModel.fetchNewsBySource(AppConstants.SOURCE_ABC_NEWS)

            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errormessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(
                topHeadlineBySourceRepository,
                times(1)
            ).getTopHeadlineBySource(AppConstants.SOURCE_ABC_NEWS)
        }
    }


    @Test
    fun fetchNewsByLanguage_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineBySourceRepository)
                .getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)

            val viewModel =
                TopHeadlineBySourceViewModel(topHeadlineBySourceRepository, dispatcherProvider)

            viewModel.fetchNewsByLanguage(AppConstants.LANGUAGE_EN)

            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<Article>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(
                topHeadlineBySourceRepository,
                times(1)
            ).getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)
        }
    }

    @Test
    fun fetchNewsByLanguage_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errormessage = "Error"

            doReturn(flow<List<Article>> {
                throw IllegalStateException(errormessage)
            })
                .`when`(topHeadlineBySourceRepository)
                .getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)

            val viewModel =
                TopHeadlineBySourceViewModel(topHeadlineBySourceRepository, dispatcherProvider)

            viewModel.fetchNewsByLanguage(AppConstants.LANGUAGE_EN)

            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errormessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(
                topHeadlineBySourceRepository,
                times(1)
            ).getTopHeadlineByLanguage(AppConstants.LANGUAGE_EN)
        }
    }

}