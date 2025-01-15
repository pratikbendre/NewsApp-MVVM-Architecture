package com.pratikbendre.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pratikbendre.newsapp.ui.countries.CountriesRoute
import com.pratikbendre.newsapp.ui.language.LanguageRoute
import com.pratikbendre.newsapp.ui.main.MainScreenRoute
import com.pratikbendre.newsapp.ui.newsSources.NewsSourceRoute
import com.pratikbendre.newsapp.ui.offlinearticle.OfflineArticleRoute
import com.pratikbendre.newsapp.ui.search.SearchRoute
import com.pratikbendre.newsapp.ui.topheadline.TopheadlineRoute
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceScreenRoute

sealed class Route(val name: String) {
    object Main : Route("main")
    object TopHeadline : Route("topheadline/{id}") {
        fun createRoute(id: String) = "topheadline/$id"
    }

    object NewsSource : Route("newsSource")
    object TopHeadlineBySource : Route("topHeadlineBySource/{filter_key}/{filter_value}") {
        fun createRoute(filter_key: String, filter_value: String) =
            "topHeadlineBySource/$filter_key/$filter_value"
    }

    object Countries : Route("countries")
    object Languages : Route("languages")
    object Search : Route("search")
    object OfflineArticle : Route("offlinearticle")
}

@Composable
fun NavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Route.Main.name
    ) {
        composable(route = Route.Main.name)
        {
            MainScreenRoute(navController)
        }
        composable(route = Route.TopHeadline.name, arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        )) { backStackEntry ->
            val countryId = backStackEntry.arguments?.getString("id")
            if (countryId != null) {
                TopheadlineRoute(countryId, onNewsClick = {
                    openCustomTab(context, it)
                })
            }
        }
        composable(route = Route.NewsSource.name) {
            NewsSourceRoute(navController)
        }
        composable(route = Route.TopHeadlineBySource.name, arguments = listOf(
            navArgument("filter_key") { type = NavType.StringType },
            navArgument("filter_value") { type = NavType.StringType }
        )) { backStackEntry ->
            val filter_key = backStackEntry.arguments?.getString("filter_key")
            val filter_value = backStackEntry.arguments?.getString("filter_value")
            if (filter_key != null && filter_value != null) {
                TopHeadlineBySourceScreenRoute(filter_key, filter_value, onNewsClick = {
                    openCustomTab(context, it)
                })
            }
        }
        composable(route = Route.Countries.name) {
            CountriesRoute(navController)
        }
        composable(route = Route.Languages.name) {
            LanguageRoute(navController)
        }
        composable(route = Route.Search.name) {
            SearchRoute(onNewsClick = {
                openCustomTab(context, it)
            })
        }
        composable(route = Route.OfflineArticle.name) {
            OfflineArticleRoute(onNewsClick = {
                openCustomTab(context, it)
            })
        }
    }
}

fun openCustomTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}