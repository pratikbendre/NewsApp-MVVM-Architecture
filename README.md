# NewsApp-MVVM-Architecture

This is a News App built to demonstrate the **MVVM** architecture in Kotlin. It leverages modern
Android technologies such as **Jetpack Compose**, **Dagger Hilt**, **Retrofit**, **RoomDB**, *
*Coroutines**, and **Flow**.

---

## Branches

- `master`
- `v1-dagger2-with-mvvm`
- `v2-Hilt-migration`
- `v3-compose-migration`

---

## Features Implemented

- **Top Headlines** from multiple sources
- **News based on Sources**, Countries, and Languages
- **Instant Search** using Flow operators:
    - Debounce
    - Filter
    - DistinctUntilChanged
    - FlatMapLatest
- **Offline News** caching and retrieval
- **Pagination** for top headlines
- **Unit Tests** using:
    - Mockito
    - Turbine
    - Espresso

---

## API Key Setup

### Steps to Configure Your API Key

1. **Obtain an API Key**:
    - Visit the [NewsAPI website](https://newsapi.org).
    - Sign up and get your free API key.

2. **Link the API Key in the Code**:
    - Replace the existing API Key in the `AppConstants.kt` file with your own.

3. **Rebuild the Project**:
    - Sync your project with Gradle and rebuild it to apply the changes.

By following these steps, you can avoid hitting the API rate limits and ensure the smooth
functioning of the app.

---

## Minimum Requirements

- **Minimum SDK Level:** 21

---

## Technologies and Libraries

- **Kotlin**: A concise, type-safe programming language that enhances Android app development.
- **Dagger Hilt**: A dependency injection library simplifying dependency management for modular and
  scalable apps.
- **Kotlin Serialization**: A high-performance library for encoding and decoding JSON and other
  formats.
- **Coroutines**: A framework for managing asynchronous programming with structured concurrency.
- **Flow**: A reactive stream API for managing asynchronous data flows in a predictable way.
- **Retrofit**: A robust HTTP client for interacting with REST APIs.
- **RoomDB**: An ORM library for interacting with SQLite databases in a structured manner.
- **Jetpack Compose**: A modern declarative UI toolkit for building native UIs with minimal code.
- **Coil**: A fast and efficient image loading library with caching and transformation support..

---

## App Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture pattern for modularity and
separation of concerns, which enhances scalability and maintainability.

![Logo](https://github.com/pratikbendre/NewsApp-MVVM-Architecture/blob/master/assets/mvvm_architecture_image.png)

---

## Project Folder Structure

```
|-- com
    |-- pratikbendre
        |-- newsapp
            |-- NewsApplication.kt
            |-- data
            |   |-- api
            |   |   |-- NetworkInterceptor.kt
            |   |   |-- NetworkService.kt
            |   |-- local
            |   |   |-- AppDatabase.kt
            |   |   |-- AppDatabaseService.kt
            |   |   |-- DatabaseService.kt
            |   |   |-- dao
            |   |   |   |-- ArticleDao.kt
            |   |   |-- entity
            |   |       |-- Article.kt
            |   |       |-- Source.kt
            |   |-- model
            |   |   |-- ArticleModel.kt
            |   |   |-- Country.kt
            |   |   |-- Language.kt
            |   |   |-- NewsSources.kt
            |   |   |-- SourceModel.kt
            |   |   |-- SourcesResponse.kt
            |   |   |-- TopHeadlinesResponse.kt
            |   |-- repository
            |       |-- CountriesRepository.kt
            |       |-- LanguageRepository.kt
            |       |-- NewsSourcesRepository.kt
            |       |-- OfflineArticleRepository.kt
            |       |-- SearchRepository.kt
            |       |-- TopHeadlineBySourceRepository.kt
            |       |-- TopHeadlinePagingRepository.kt
            |       |-- TopHeadlinePagingSource.kt
            |       |-- TopHeadlineRepository.kt
            |-- di
            |   |-- module
            |   |   |-- ApplicationModule.kt
            |   |-- qualifiers.kt
            |-- ui
            |   |-- base
            |   |   |-- CommanUI.kt
            |   |   |-- LanguageFilterBottomSheet.kt
            |   |   |-- NewsNavigation.kt
            |   |   |-- UiState.kt
            |   |-- countries
            |   |   |-- CountriesScreen.kt
            |   |   |-- CountriesViewModel.kt
            |   |-- language
            |   |   |-- LanguageScreen.kt
            |   |   |-- LanguageViewModel.kt
            |   |-- main
            |   |   |-- MainActivity.kt
            |   |   |-- MainScreen.kt
            |   |-- newsSources
            |   |   |-- NewsSourceScreen.kt
            |   |   |-- NewsSourcesViewModel.kt
            |   |-- offlinearticle
            |   |   |-- OfflineArticleScreen.kt
            |   |   |-- OfflineArticleViewModel.kt
            |   |-- search
            |   |   |-- SearchScreen.kt
            |   |   |-- SearchViewModel.kt
            |   |-- theme
            |   |   |-- Color.kt
            |   |   |-- Theme.kt
            |   |   |-- Type.kt
            |   |-- topheadline
            |   |   |-- TopHeadlineScreen.kt
            |   |   |-- TopHeadlineViewModel.kt
            |   |-- topheadlinebysource
            |   |   |-- TopHeadlineBySourceScreen.kt
            |   |   |-- TopHeadlineBySourceViewModel.kt
            |   |-- topheadlinepagination
            |       |-- TopHeadlinePaginationScreen.kt
            |       |-- TopHeadlinePaginationViewModel.kt
            |-- utils
                |-- AppConstants.kt
                |-- DispatcherProvider.kt
                |-- NetworkHelper.kt
                |-- typealias.kt
```
