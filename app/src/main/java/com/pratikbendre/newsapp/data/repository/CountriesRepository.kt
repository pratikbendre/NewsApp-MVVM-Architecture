package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class CountriesRepository {
    fun getCountries(): Flow<List<Country>> {
        return flow { emit(getCountriesList()) }.map {
            it
        }
    }

    private fun getCountriesList(): List<Country> {
        return listOf(
            Country("Argentina", "AR"),
            Country("Australia", "AU"),
            Country("Austria", "AT"),
            Country("Belgium", "BE"),
            Country("Brazil", "BR"),
            Country("Bulgaria", "BG"),
            Country("Canada", "CA"),
            Country("China", "CN"),
            Country("Colombia", "CO"),
            Country("Cuba", "CU"),
            Country("Czech Republic", "CZ"),
            Country("Egypt", "EG"),
            Country("France", "FR"),
            Country("Germany", "DE"),
            Country("Greece", "GR"),
            Country("Hong Kong", "HK"),
            Country("Hungary", "HU"),
            Country("India", "IN"),
            Country("Indonesia", "ID"),
            Country("Ireland", "IE"),
            Country("Israel", "IL"),
            Country("Italy", "IT"),
            Country("Japan", "JP"),
            Country("Latvia", "LV"),
            Country("Lithuania", "LT"),
            Country("Malaysia", "MY"),
            Country("Morocco", "MA"),
            Country("Mexico", "MX"),
            Country("Netherlands", "NL"),
            Country("New Zealand", "NZ"),
            Country("Nigeria", "NG"),
            Country("Norway", "NO"),
            Country("Philippines", "PH"),
            Country("Poland", "PL"),
            Country("Portugal", "PT"),
            Country("Romania", "RO"),
            Country("Russia", "RU"),
            Country("Saudi Arabia", "SA"),
            Country("Serbia", "RS"),
            Country("Singapore", "SG"),
            Country("Slovakia", "SK"),
            Country("Slovenia", "SI"),
            Country("South Africa", "ZA"),
            Country("South Korea", "KR"),
            Country("Spain", "ES"),
            Country("Sweden", "SE"),
            Country("Switzerland", "CH"),
            Country("Taiwan", "TW"),
            Country("Thailand", "TH"),
            Country("Turkey", "TR"),
            Country("Ukraine", "UA"),
            Country("United Arab Emirates", "AE"),
            Country("United Kingdom", "GB"),
            Country("United States", "US"),
            Country("Venezuela", "VE")
        )
    }
}