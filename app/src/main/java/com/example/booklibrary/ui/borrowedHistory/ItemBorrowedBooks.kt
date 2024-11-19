package com.example.booklibrary.ui.borrowedHistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse

@Composable
fun ItemBorrowedBooks(
    book: BookCheckoutResponse,
    onBorrowedBookClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onBorrowedBookClick(book.bookISBN)
            }
            .height(100.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = book.bookTitle,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.date_borrowed),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
            Text(
                text = book.dateBorrowed,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.date_returned),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
            Text(
                text = book.dateReturned ?: "To be returned ...",
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.scheduled_return),
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
            )
            Text(
                text = book.scheduledReturnDate,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
        }
    }
}
