package com.example.booklibrary.ui.requested

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.viewModels.AuthViewModel
import com.example.booklibrary.data.book.viewModels.RequestedBookViewModel
import com.example.booklibrary.util.Resource
import kotlinx.coroutines.launch

@Composable
fun RequestedScreen(
    onAddNewBook: () -> Unit,
    onClickedBook: (String) -> Unit,
    viewModel: RequestedBookViewModel = hiltViewModel(),
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    var isFiltered by remember { mutableStateOf(true) }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedStatusFilter by remember {
        mutableStateOf("")
    }
    val isUserAdmin by authViewModel.userAdmin.collectAsState()
    val scope = rememberCoroutineScope()
    val listOfBooks = viewModel.books.collectAsState().value
    var swipedDown by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(swipedDown) {
        viewModel.getAllRequestedBooks()
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.requested_books),
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp),
                    onClick = {
                        onAddNewBook()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp),
                    onClick = {
                        isFiltered = !isFiltered
                    },
                ) {
                    if (isFiltered) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = stringResource(id = R.string.filter_applied),
                            modifier = Modifier.size(32.dp)
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter_two),
                            contentDescription = stringResource(id = R.string.filter),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                if (isUserAdmin) {
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
                            BookStatus.entries.forEach { bookStatus ->
                                DropdownMenuItem(
                                    text = { Text(text = bookStatus.name) },
                                    onClick = {
                                        expanded = false
                                        selectedStatusFilter = bookStatus.name
                                        scope.launch {
                                            viewModel.getRequestedBooksByBookStatus(
                                                selectedStatusFilter
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            when (listOfBooks) {
                is Resource.Success -> {
                    listOfBooks.data?.let { books ->
                        val sortedBooks = if (isFiltered) {
                            books.sortedByDescending { it.likeCounter }
                        } else {
                            books.sortedBy { it.likeCounter }
                        }
                        items(sortedBooks) { book ->
                            ItemRequestedBook(
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
    }
}