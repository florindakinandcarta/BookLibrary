package com.example.booklibrary.ui.borrowedHistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.models.ReturnStatus
import com.example.booklibrary.util.Resource
import com.example.booklibrary.viewModels.BookCheckoutViewModel
import com.example.booklibrary.viewModels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun BookCheckoutsScreen(
    onBorrowedBookClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onGetByStatusClicked: (String) -> Unit,
) {
    val isUserAdmin = userViewModel.isUserAdminFlow.collectAsState(initial = false)
    val listOfBooks = bookCheckoutViewModel.bookCheckouts.collectAsState().value
    val books = bookCheckoutViewModel.books.collectAsState().value
    val scope = rememberCoroutineScope()
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedStatusFilter by remember {
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.borrowed_history),
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                Spacer(Modifier.weight(1f))
                if (isUserAdmin.value) {
                    IconButton(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp),
                        onClick = {
                            onSearchClick()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentSize(Alignment.TopEnd)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(6.dp)
                                .size(24.dp),
                            onClick = {
                                expanded = !expanded
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FilterAlt,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            ReturnStatus.entries.forEach { returnStatus ->
                                DropdownMenuItem(
                                    text = { Text(text = returnStatus.displayName) },
                                    onClick = {
                                        expanded = false
                                        selectedStatusFilter = returnStatus.name
                                        onGetByStatusClicked(returnStatus.name)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxHeight()
        ) {
            if (isUserAdmin.value) {
                item {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            bookCheckoutViewModel.getAllBookCheckouts()
                        }
                    }
                }
                when (books) {
                    is Resource.Success -> {
                        books.data?.let { bookItems ->
                            items(bookItems) { item ->
                                ItemBorrowedBooksAdmin(
                                    book = item,
                                    onBorrowedBookClick = onBorrowedBookClick
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        books.message?.let { error ->
                            println(error)
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
                }
            } else {
                item {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            bookCheckoutViewModel.getAllBookCheckoutsForUser()
                        }
                    }
                }
                when (listOfBooks) {
                    is Resource.Success -> {
                        listOfBooks.data?.let { books ->
                            items(books) { book ->
                                ItemBorrowedBooks(
                                    book = book,
                                    onBorrowedBookClick = onBorrowedBookClick
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
                        listOfBooks.message?.let { error ->
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
