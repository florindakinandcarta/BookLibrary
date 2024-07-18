package com.example.booklibrary.ui.searchNewBook

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.booklibrary.R
import com.example.booklibrary.data.googleBooks.Items
import com.example.booklibrary.ui.requested.GoogleBooksViewModel
import com.example.booklibrary.util.Resource


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SearchWithGoogleBookScreen(
    onBackClicked: () -> Unit,
    onBookClicked: (Items) -> Unit
) {
    val searchBookViewModel: GoogleBooksViewModel = hiltViewModel()
    val responseBooks by searchBookViewModel.responseBooks.collectAsState()
    val loadingBooks by searchBookViewModel.loadingBooks.collectAsState()
    val isSaved by searchBookViewModel.isSaved.collectAsState()
    val isResponseZero by searchBookViewModel.isResponseZero.collectAsState()
    val (isSearching, setIsSearching) = remember { mutableStateOf(false) }
    val (searchText, setSearchText) = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (isSearching) {
        BackHandler {
            setIsSearching(false)
        }
    }
    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                query = searchText,
                onQueryChange = {
                    setSearchText(it)
                },
                onSearch = {
                    setSearchText(it)
                    keyboardController?.hide()
                    searchBookViewModel.fetchBooks(queryBookName = it, loadMore = 0)
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
                    setIsSearching(it)
                },
                trailingIcon = {
                    if (isSearching || searchText.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            modifier = Modifier.clickable {
                                setIsSearching(false)
                                setSearchText("")
                                searchBookViewModel.clearSearchResults()
                            },
                            style = TextStyle(color = Color.DarkGray)
                        )
                    } else {
                        searchBookViewModel.clearSearchResults()
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        )
                    )
                },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White,
                    dividerColor = Color.Black,
                    inputFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black
                    )
                ),
            ) {
                when {
                    loadingBooks -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    responseBooks is Resource.Success -> {
                        val books = responseBooks?.data
                        books?.let {
                            LazyColumn {
                                items(it.items) { book ->
                                    ItemSearchGoogleBook(book = book, onBookClicked = onBookClicked)
                                }
                            }
                        } ?: run {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = stringResource(id = R.string.no_books_found),
                                    color = Color.Gray,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }

                    responseBooks is Resource.Error -> {
                        val errorMessage = (responseBooks as Resource.Error).message
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = errorMessage ?: stringResource(id = R.string.error_occurred),
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    isResponseZero -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = stringResource(id = R.string.no_results),
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    else -> {
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.search_book_with),
                color = Color.Gray,
            )
            GlideImage(
                model = R.drawable.google_pooks_logo_2015,
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}