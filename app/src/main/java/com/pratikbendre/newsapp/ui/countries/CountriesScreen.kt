package com.pratikbendre.newsapp.ui.countries

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
import com.pratikbendre.newsapp.data.model.Country
import com.pratikbendre.newsapp.ui.base.Route
import com.pratikbendre.newsapp.ui.base.ShowError
import com.pratikbendre.newsapp.ui.base.ShowLoading
import com.pratikbendre.newsapp.ui.base.SimpleButton
import com.pratikbendre.newsapp.ui.base.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesRoute(
    navController: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val countriesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = "Countries") })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(navController, countriesUiState, viewModel::fetchCountries)
        }
    })
}

@Composable
fun CountriesScreen(
    navController: NavController, uiState: UiState<List<Country>>,
    onretry: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            CountriesList(navController, uiState.data)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(onClick = onretry, uiState.message)
        }
    }
}

@Composable
fun CountriesList(navController: NavController, countries: List<Country>) {
    LazyColumn {
        items(countries, key = { country -> country.countryCode }) { country ->
            CountryCard(navController, country)
        }
    }
}

@Composable
fun CountryCard(navController: NavController, country: Country) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SimpleButton(onClick = {
            navController.navigate(Route.TopHeadline.createRoute(country.countryCode))
        }, title = country.countryName)
    }
}