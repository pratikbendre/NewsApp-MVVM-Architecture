package com.pratikbendre.newsapp.ui.topheadlinebysourcescreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.model.SourceModel
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceScreen
import org.junit.Rule
import org.junit.Test

class TopHeadlineBySourceScreenTestModel {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            TopHeadlineBySourceScreen(newsBySourceuiState = UiState.Loading, onNewsClick = {})
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading))
            .assertExists()
    }


    @Test
    fun articles_whenUiStateIsSuccess_isShown() {

        composeTestRule.setContent {
            TopHeadlineBySourceScreen(
                newsBySourceuiState = UiState.Success(testArticleModels),
                onNewsClick = {})
        }

        composeTestRule.onNodeWithText(
            testArticleModels[0].title, substring = true
        ).assertExists().assertHasClickAction()


        composeTestRule.onNode(hasScrollToNodeAction()).performScrollToNode(
            hasText(
                testArticleModels[5].title, substring = true
            )
        )


        composeTestRule.onNodeWithText(
            testArticleModels[5].title, substring = true
        ).assertExists().assertHasClickAction()
    }


    @Test
    fun error_whenUiStateIsError_isShown() {
        val errorMessage = "Error Message For You"

        composeTestRule.setContent {
            TopHeadlineBySourceScreen(
                newsBySourceuiState = UiState.Error(errorMessage),
                onNewsClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}


private val testArticleModels = listOf(
    ArticleModel(
        title = "title1",
        description = "description1",
        url = "url1",
        imageurl = "imageUrl1",
        sourceModel = SourceModel(id = "sourceId1", name = "sourceName1")
    ), ArticleModel(
        title = "title2",
        description = "description2",
        url = "url2",
        imageurl = "imageUrl2",
        sourceModel = SourceModel(id = "sourceId2", name = "sourceName2")
    ), ArticleModel(
        title = "title3",
        description = "description3",
        url = "url3",
        imageurl = "imageUrl3",
        sourceModel = SourceModel(id = "sourceId3", name = "sourceName3")
    ), ArticleModel(
        title = "title4",
        description = "description4",
        url = "url4",
        imageurl = "imageUrl4",
        sourceModel = SourceModel(id = "sourceId4", name = "sourceName4")
    ), ArticleModel(
        title = "title5",
        description = "description5",
        url = "url5",
        imageurl = "imageUrl5",
        sourceModel = SourceModel(id = "sourceId5", name = "sourceName5")
    ), ArticleModel(
        title = "title6",
        description = "description6",
        url = "url6",
        imageurl = "imageUrl6",
        sourceModel = SourceModel(id = "sourceId6", name = "sourceName6")
    )
)