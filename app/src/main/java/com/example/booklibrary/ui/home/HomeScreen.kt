package com.example.booklibrary.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.ui.ItemBook

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(
    onClickedBook: (Book) -> Unit,
    onNotificationClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var selectedFilter by remember { mutableStateOf("") }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedLanguage by remember {
        mutableStateOf("Languages")
    }

    Scaffold(
        topBar = { TopBarHome(onNotificationClick, onSearchClick) },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxHeight()
        ) {
            item {
                ComposeSwipeablePages()
            }
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        item {
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = it },
                            ) {
                                AssistChip(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .width(140.dp),
                                    onClick = {},
                                    label = {
                                        Text(
                                            text = selectedLanguage,
                                            style = TextStyle(
                                                color = Color.Black
                                            )
                                        )
                                    },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expanded
                                        )
                                    },
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    modifier = Modifier
                                        .width(140.dp)
                                        .background(Color.White)
                                ) {
                                    SampleData.languages.forEach { option ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    option,
                                                    style = TextStyle(color = Color.Black)
                                                )
                                            },
                                            onClick = {
                                                selectedLanguage = option
                                                expanded = false
                                            },
                                        )
                                    }
                                }
                            }
                        }
                        items(SampleData.genres) { option ->
                            FilterChip(
                                selected = option == selectedFilter,
                                onClick = {
                                    selectedFilter = option
                                },
                                label = {
                                    Text(text = option)
                                }
                            )
                        }
                    }
                }
            }

            items(SampleData.books.filter { book ->
                if (selectedLanguage == "Languages") {
                    true
                } else {
                    (selectedLanguage.isEmpty() || book.language == selectedLanguage) &&
                            (selectedFilter.isEmpty() || book.genre == selectedFilter)
                }
            }) { book ->
                ItemBook(
                    book = book,
                    onClickedBook
                )
            }

        }
    }
}