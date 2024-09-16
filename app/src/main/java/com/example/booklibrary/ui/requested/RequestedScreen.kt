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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.data.book.viewModels.AuthViewModel

@Composable
fun RequestedScreen(
    onAddNewBook: () -> Unit,
    onClickedBook: (String) -> Unit,
//    orderBooksBasedOnLikes: () -> Unit
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
                            SampleData.bookStatus.forEach { bookStatus ->
                                DropdownMenuItem(
                                    text = { Text(text = bookStatus) },
                                    onClick = {
                                        expanded = false
                                        selectedStatusFilter = bookStatus
                                    })
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        val filteredBooks = if (selectedStatusFilter.isNotEmpty()) {
            SampleData.books.filter { it.status == selectedStatusFilter }
        } else {
            SampleData.books
        }

        val sortedBooks = if (isFiltered) {
            filteredBooks.sortedByDescending { it.numberOfLikes }
        } else {
            filteredBooks.sortedBy { it.numberOfLikes }
        }
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            items(sortedBooks) { book ->
                ItemRequestedBook(
                    book = book,
                    onClickedBook = onClickedBook
                )
            }
        }
    }
}