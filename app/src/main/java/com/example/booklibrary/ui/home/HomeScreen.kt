package com.example.booklibrary.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.data.models.Genres
import com.example.booklibrary.data.models.Languages
import com.example.booklibrary.ui.ItemBook
import com.example.booklibrary.util.Resource
import com.example.booklibrary.viewModels.BookViewModel
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
    onClickedBook: (String) -> Unit,
    onGetBookByGenreClicked: (String) -> Unit,
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
                        modifier =
                        if (isSystemInDarkTheme()) {
                            Modifier.background(androidx.compose.material3.MaterialTheme.colorScheme.surfaceDim)
                        } else {
                            Modifier.background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                        }
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
                                    ) {
                                        Languages.entries.forEach { languages ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(languages.name)
                                                },
                                                onClick = {
                                                    selectedLanguage = languages.name
                                                    onSelectedLanguageClick(languages.name)
                                                    expanded = false
                                                    selectedFilter = ""
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                            items(Genres.entries) { genre ->
                                FilterChip(
                                    selected = genre.displayName == selectedFilter,
                                    onClick = {
                                        selectedFilter = genre.displayName
                                        onGetBookByGenreClicked(selectedFilter)
                                    },
                                    label = {
                                        Text(text = genre.displayName)
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
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(64.dp),
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        listOfBooks.data?.let { error ->
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = error.toString(),
                                        modifier = Modifier
                                            .padding(8.dp),
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold,
                                        ),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
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