package com.pratikbendre.newsapp.ui.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.ui.base.ShowArticle
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = "Search") })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SearchBar(viewModel)
            Spacer(modifier = Modifier.height(4.dp))
            SearchScreen(uiState, onNewsClick)
        }
    })
}

@Composable
fun SearchScreen(uiState: UiState<List<Article>>, onNewsClick: (url: String) -> Unit) {
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
fun SearchBar(viewModel: SearchViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(300)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .collect { query ->
                viewModel.fetchnews(query)
            }
    }
    BasicTextField(
        value = searchQuery,
        onValueChange = { newValue ->
            searchQuery = newValue
        },
        textStyle = TextStyle(color = Color.White),  // Set text color to white
        cursorBrush = SolidColor(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(8.dp)
            .border(1.dp, Color.White, RectangleShape),
        singleLine = true,
        decorationBox = { innerTextField ->
            if (searchQuery.isEmpty()) {
                Text(
                    text = "Search...",
                    color = Color.Gray,
                    modifier = Modifier.padding(2.dp)
                )
            }
            innerTextField()
        }
    )
}