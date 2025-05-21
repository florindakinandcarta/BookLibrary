package com.example.booklibrary.ui.borrowedHistory

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booklibrary.R
import com.example.booklibrary.ui.home.SearchViewModel
import com.example.booklibrary.util.Resource
import com.example.booklibrary.viewModels.BookCheckoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrowedSearchScreen(
    onBackClicked: () -> Unit,
    onSearchClick: (String) -> Unit,
    onClickedBook: (String) -> Unit,
    placeholderText: String,
    bookViewModel: BookCheckoutViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = viewModel()
) {
    val books = bookViewModel.books.collectAsState().value
    val searchText by searchViewModel.searchText.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        if (isSearching) {
            BackHandler {
                searchViewModel.onToggleSearch()
            }
        }
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            query = searchText,
            onQueryChange = {
                searchViewModel.onSearchTextChange(it)
            },
            onSearch = {
                searchViewModel.onSearchTextChange(it)
                keyboardController?.hide()
                onSearchClick(searchText)
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .size(38.dp)
                        .padding(end = 8.dp),
                    onClick = {
                        onBackClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            active = isSearching,
            onActiveChange = {
                searchViewModel.onToggleSearch()
            },
            trailingIcon = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            searchViewModel.onToggleSearch()
                        },
                )
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(bottom = 60.dp)
            ) {
                when (books) {
                    is Resource.Success -> {
                        books.data?.let { books ->
                            items(books) { book ->
                                ItemBorrowedBooksAdmin(
                                    book = book,
                                    onBorrowedBookClick = onClickedBook
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
                        books.message?.let { error ->
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
        }
    }
}
