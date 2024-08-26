package com.example.booklibrary.ui.searchNewBook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.viewModels.BookViewModel
import com.example.booklibrary.ui.bookDetails.AboutBook
import com.example.booklibrary.util.Resource


@Composable
fun GoogleBookDetails(
    bookISBN: String,
    onBackClicked: () -> Unit,
    onEditReviewClicked: () -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {
    val bookDetails = viewModel.bookDetails.collectAsState().value
    LaunchedEffect(bookISBN) {
        viewModel.getBookByISBN(bookISBN)
    }
    Scaffold(
        topBar = {
            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp),
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
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            if (bookDetails is Resource.Success) {
                bookDetails.data?.let { book ->
                    item {
                        BookDetailsSectionGoogle(book)
                    }
                    item {
                        AboutBook(book)
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    /*TODO()*/
                                },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                shape = RoundedCornerShape(32.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.request_book),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
