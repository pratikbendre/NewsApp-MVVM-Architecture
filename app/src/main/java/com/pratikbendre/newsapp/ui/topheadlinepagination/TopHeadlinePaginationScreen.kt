package com.pratikbendre.newsapp.ui.topheadlinepagination

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.model.SourceModel
import com.pratikbendre.newsapp.ui.base.DescriptionText
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlinePaginationRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlinePaginationViewModel = hiltViewModel()
) {
    val articles = viewModel.uiState.collectAsLazyPagingItems()
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = "News Sources") })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlinePaginationScreen(articles, onNewsClick)
        }
    })
}

@Composable
fun TopHeadlinePaginationScreen(
    articles: LazyPagingItems<ArticleModel>,
    onNewsClick: (url: String) -> Unit
) {
    ArticleList(articles = articles, onNewsClick = onNewsClick)
    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ShowError(onClick = {}, error.error.localizedMessage!!)
            }

            loadState.append is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.append is LoadState.Error -> {
                val error = articles.loadState.append as LoadState.Error
                ShowError(onClick = {}, error.error.localizedMessage!!)
            }
        }
    }
}

@Composable
fun ArticleList(articles: LazyPagingItems<ArticleModel>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]!!.url }) { index ->
            ShowArticle(articles[index]!!, onNewsClick)
        }
    }
}

@Composable
fun ShowArticle(article: ArticleModel, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        BannerImage(article)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.sourceModel)
    }
}

@Composable
fun BannerImage(article: ArticleModel) {
    AsyncImage(
        model = article.imageurl, contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun SourceText(source: SourceModel) {
    Text(
        text = source.name,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}