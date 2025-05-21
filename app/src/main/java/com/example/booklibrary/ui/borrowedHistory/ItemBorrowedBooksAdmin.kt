package com.example.booklibrary.ui.borrowedHistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.data.models.response.BookCheckoutWithUserAndBookItemResponse

@Composable
fun ItemBorrowedBooksAdmin(
    book: BookCheckoutWithUserAndBookItemResponse,
    onBorrowedBookClick: (String) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable {
                    book.bookISBN?.let { onBorrowedBookClick(it) }
                }
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            book.bookTitle?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = "User: ${book.userFullName}",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 14.sp,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = "Date borrowed: ${book.dateBorrowed}",
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
            Text(
                text = "Date returned: ${book.dateReturned ?: "To be returned ..."}",
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
            Text(
                text = "Scheduled date to return: ${book.scheduledReturnDate}",
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
        }
    }
}