package com.example.booklibrary.ui.requested

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.SampleData

@Composable
fun RequestedScreen(
    onAddNewBook: () -> Unit,
    onClickedBook: (Book) -> Unit
//    orderBooksBasedOnLikes: () -> Unit
) {
    val (isFiltered, setIsFiltered) = remember { mutableStateOf(true) }
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
                        setIsFiltered(!isFiltered)
                    },
                ) {
                    if (isFiltered) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = "Filter Applied",
                            modifier = Modifier.size(32.dp)
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter_two),
                            contentDescription = "Filter",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            if (isFiltered) {
                items(SampleData.books.sortedByDescending {
                    it.numberOfLikes
                }) { book ->
                    ItemRequestedBook(
                        book = book,
                        onClickedBook = onClickedBook
                    )
                }
            } else {
                items(SampleData.books.sortedBy {
                    it.numberOfLikes
                }) { book ->
                    ItemRequestedBook(
                        book = book,
                        onClickedBook = onClickedBook
                    )
                }
            }

        }
    }
}