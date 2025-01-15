package com.pratikbendre.newsapp.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.pratikbendre.newsapp.ui.base.Route
import com.pratikbendre.newsapp.ui.base.SimpleButton
import com.pratikbendre.newsapp.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenRoute(navController: NavHostController) {
    val buttonItems: List<Pair<String, String>> = listOf(
        "Top Headlines" to Route.TopHeadline.createRoute(AppConstants.COUNTRY_US),
        "News Sources" to Route.NewsSource.name,
        "Countries" to Route.Countries.name,
        "Language" to Route.Languages.name,
        "Search" to Route.Search.name,
        "Offline Article" to Route.OfflineArticle.name
    )
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstants.APP_NAME) })
    }, content = { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonList(navController, buttonItems)
        }
    })
}

@Composable
fun ButtonList(navController: NavHostController, buttonTitles: List<Pair<String, String>>) {
    buttonTitles.forEach { (title, route) ->
        SimpleButton(
            onClick = { navController.navigate(route) },
            title = title
        )
    }
}
