package com.example.booklibrary.ui.borrowedHistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.ui.theme.BookLibraryTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemBorrowedBooks(
    book: Book,
    onBorrowedBookClick: (Book) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                onBorrowedBookClick(book)
            }
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = "",
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
                .height(150.dp)
                .width(100.dp),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.reading_time)
        )
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = book.author.toString(),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
            Text(
                text = book.title.toString(),
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
                    text = stringResource(id = R.string.date_borrowed_number),
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
                    text = stringResource(id = R.string.date_returned_number),
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
}

@Preview(showBackground = true)
@Composable
fun PreviewItemBorrowedBooks() {
    BookLibraryTheme {
        ItemBorrowedBooks(book = Book()) {
        }
    }
}