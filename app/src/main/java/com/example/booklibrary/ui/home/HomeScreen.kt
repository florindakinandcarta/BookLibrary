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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.data.book.models.Languages
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.ui.ItemBook
import com.example.booklibrary.util.Resource

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(
    officeName: String,
    onNotificationClick: () -> Unit,
    viewModel: BookViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onClickedBook: (String) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("") }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedLanguage by remember {
        mutableStateOf("Languages")
    }
    val listOfBooks = viewModel.books.collectAsState().value
    LaunchedEffect(officeName) {
        viewModel.getAvailableBooks(officeName)
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
                                    Languages.entries.forEach { option ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    option.name,
                                                    style = TextStyle(color = Color.Black)
                                                )
                                            },
                                            onClick = {
                                                selectedLanguage = option.name
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
            if (listOfBooks is Resource.Success) {
                listOfBooks.data?.let { books ->
                    items(books) { book ->
                        ItemBook(
                            book = book,
                            onClickedBook = onClickedBook
                        )
                    }
                }
            }
        }
    }
}