package com.example.booklibrary.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.data.book.models.Languages
import com.example.booklibrary.data.book.models.displayName
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.ui.ItemBook
import com.example.booklibrary.util.Resource
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class
)
@Composable
fun HomeScreen(
    onNotificationClick: () -> Unit,
    viewModel: BookViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onSelectedLanguageClick: (String) -> Unit,
    onClickedBook: (String) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("") }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedLanguage by remember {
        mutableStateOf("Languages")
    }
    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                viewModel.getAllBooks()
            }
        }
    )
    val listOfBooks = viewModel.books.collectAsState().value
    Scaffold(
        topBar = { TopBarHome(onNotificationClick, onSearchClick) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
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
                                        Languages.entries.forEach { languages ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        languages.name,
                                                        style = TextStyle(color = Color.Black)
                                                    )
                                                },
                                                onClick = {
                                                    selectedLanguage = languages.name
                                                    onSelectedLanguageClick(languages.name)
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
                                        scope.launch {
//                                        viewModel.getBooksByGenre(selectedFilter)
                                        }
                                    },
                                    label = {
                                        Text(text = option)
                                    }
                                )
                            }
                        }
                    }
                }
                when (listOfBooks) {
                    is Resource.Success -> {
                        listOfBooks.data?.let { books ->
                            items(books) { book ->
                                ItemBook(
                                    book = book,
                                    onClickedBook = onClickedBook
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        //loader display
                    }

                    is Resource.Error -> {
                        listOfBooks.data?.let { error ->
                            //call the dialog pop up for error display it for 5s and dismiss it
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}