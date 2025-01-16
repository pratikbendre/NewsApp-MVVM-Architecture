package com.pratikbendre.newsapp.ui.topheadlinebysource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.ui.base.ShowArticle
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineBySourceScreenRoute(
    filter_key: String,
    filter_value: String,
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlineBySourceViewModel = hiltViewModel()
) {
    val newsBySourceuiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(filter_key) {
        when (filter_key) {
            AppConstants.SOURCE -> viewModel.fetchNewsBySource(filter_value)
            AppConstants.LANGUAGE -> viewModel.fetchNewsByLanguage(filter_value)
        }
    }
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = "Top Headline") })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineBySourceScreen(newsBySourceuiState, onNewsClick)
        }
    })
}

@Composable
fun TopHeadlineBySourceScreen(
    newsBySourceuiState: UiState<List<ArticleModel>>, onNewsClick: (url: String) -> Unit
) {
    when (newsBySourceuiState) {
        is UiState.Success -> {
            ArticleList(newsBySourceuiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(onClick = { }, newsBySourceuiState.message)
        }
    }
}

@Composable
private fun ArticleList(articleModels: List<ArticleModel>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articleModels, key = { article -> article.url }) { article ->
            ShowArticle(article, onNewsClick)
        }
    }
}