package com.pratikbendre.newsapp.ui.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.ui.base.Route
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.SimpleButton
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageRoute(
    navController: NavController,
    viewModel: LanguageViewModel = hiltViewModel()
) {

    val languageUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = "Languages") })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LanguageScreen(navController, languageUiState)
        }
    })
}


@Composable
fun LanguageScreen(navController: NavController, uiState: UiState<List<Language>>) {

    when (uiState) {
        is UiState.Success -> {
            LanguageList(navController, uiState.data)
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
fun LanguageList(navController: NavController, languages: List<Language>) {
    LazyColumn {
        items(languages, key = { language -> language.Code }) { language ->
            Language(navController, language)
        }
    }
}


@Composable
fun Language(navController: NavController, language: Language) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SimpleButton(onClick = {
            navController.navigate(
                Route.TopHeadlineBySource.createRoute(
                    AppConstants.LANGUAGE,
                    language.Code
                )
            )
        }, title = language.Name)
    }
}