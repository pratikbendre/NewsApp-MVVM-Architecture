package com.pratikbendre.newsapp.ui.topheadline

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.ui.base.LanguageFilterBottomSheet
import com.pratikbendre.newsapp.ui.base.ShowArticle
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.language.LanguageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopheadlineRoute(
    countryId: String,
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val languageUiState by languageViewModel.uiState.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }
    LaunchedEffect(countryId) {
        viewModel.fetchNewsByCountry(countryId)
    }
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, titleContentColor = Color.White
        ), title = { Text(text = "Top Headline") })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { showBottomSheet = true }) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(uiState, onNewsClick)
            if (showBottomSheet) {
                LanguageFilterScreen(
                    uiState = languageUiState,
                    onDismissRequest = { showBottomSheet = false },
                    onApplyFilters = { filters ->
                        Log.d("TAG", "TopheadlineRoute: " + filters)
                        val filterList = filters.toList()
                        viewModel.fetchNewsByTwoLanguage(
                            filterList.get(0).Code,
                            filterList.get(1).Code
                        )
                    })
            }
        }
    })
}

@Composable
fun TopHeadlineScreen(uiState: UiState<List<Article>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}

@Composable
private fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles, key = { article -> article.url }) { article ->
            ShowArticle(article, onNewsClick)
        }
    }
}

@Composable
fun LanguageFilterScreen(
    uiState: UiState<List<Language>>,
    onDismissRequest: () -> Unit,
    onApplyFilters: (Set<Language>) -> Unit,
) {
    when (uiState) {
        is UiState.Success -> {
            LanguageFilterBottomSheet(
                languages = uiState.data,
                onDismissRequest = onDismissRequest,
                onApplyFilters = onApplyFilters,
            )
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}