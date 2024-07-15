package com.example.booklibrary.ui.searchNewBook

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.googleBooks.Items
import com.example.booklibrary.ui.RatingBar
import com.example.booklibrary.util.toHttpsUrl


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemSearchGoogleBook(
    book: Items,
    onBookClicked: (Items) -> Unit
) {
    book.volumeInfo?.let { bookItem ->
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    onBookClicked(book)
                }
        ) {
            GlideImage(
                model = bookItem.imageLinks?.thumbnail?.toHttpsUrl(),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .height(110.dp)
                    .width(85.dp),
                contentScale = ContentScale.Fit,
                loading = placeholder(R.drawable.reading_time)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                bookItem.categories.forEach { bookCategory ->
                    Text(
                        text = AnnotatedString(bookCategory),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 8.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                }

                Text(
                    text = bookItem.title.toString(),
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
                bookItem.authors.forEach { bookAuthor ->
                    Text(
                        text = AnnotatedString(bookAuthor),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .height(30.dp)
                            .width(20.dp)
                    ) {
                        RatingBar(rating = 1.0, onRatingChanged = {})
                    }
                    if (bookItem.averageRating == null) {
                        Text(
                            text = "N/A",
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                            style = TextStyle(
                                fontSize = 12.sp,
                            ),
                        )
                    } else {
                        Text(
                            text = AnnotatedString(bookItem.averageRating.toString()),
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                            style = TextStyle(
                                fontSize = 12.sp,
                            ),
                        )
                    }
                }
            }
        }
    }
}