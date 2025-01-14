package com.pratikbendre.newsapp.ui.base

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pratikbendre.newsapp.data.model.Language
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LanguageFilterBottomSheet(
    languages: List<Language>,
    onDismissRequest: () -> Unit,
    onApplyFilters: (Set<Language>) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    var selectedFilters by remember { mutableStateOf(setOf<Language>()) }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Text(
            text = "Filters",
            modifier = Modifier.padding(8.dp)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            languages.forEach { language ->
                LanguageItem(
                    language = language,
                    isSelected = selectedFilters.contains(language),
                    onClick = {
                        selectedFilters = if (selectedFilters.contains(language)) {
                            selectedFilters - language
                        } else if (selectedFilters.size < 2) {
                            // Add language to selection if limit not reached
                            selectedFilters + language
                        } else {
                            // Do nothing if limit is reached
                            selectedFilters
                        }
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { selectedFilters = setOf() },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Reset")
            }
            Button(
                onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        onApplyFilters(selectedFilters)
                        onDismissRequest()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Apply")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageItem(
    language: Language,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    androidx.compose.material3.FilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = Modifier.padding(4.dp),
        label = { Text(text = language.Name, overflow = TextOverflow.Ellipsis) },
        interactionSource = interactionSource,
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )
}