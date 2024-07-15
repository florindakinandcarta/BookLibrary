package com.example.booklibrary.ui.searchNewBook

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun BookDetailsSectionGoogle(book: Items) {
    book.volumeInfo?.let { bookItem ->
        Column {
            GlideImage(
                model = bookItem.imageLinks?.thumbnail?.toHttpsUrl(),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .height(250.dp)
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                loading = placeholder(R.drawable.reading_time)
            )
            Text(
                text = AnnotatedString(bookItem.title.toString()),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            bookItem.authors.forEach { bookAuthor ->
                Text(
                    text = AnnotatedString(bookAuthor),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {

                        },
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (bookItem.averageRating == null) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    ) {
                        RatingBar(rating = 0.0, onRatingChanged = {})
                    }
                    Text(
                        text = "N/A",
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                        ),
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    ) {
                        RatingBar(rating = bookItem.averageRating, onRatingChanged = {})
                    }
                    Text(
                        text = AnnotatedString((bookItem.averageRating.toString())),
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                        ),
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = AnnotatedString(bookItem.pageCount.toString()),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.pages),
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 10.sp,
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = AnnotatedString(bookItem.language.toString().uppercase()),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.language),
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 10.sp,
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    bookItem.categories.forEach { bookCategory ->
                        Text(
                            text = AnnotatedString(bookCategory),
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .align(Alignment.CenterHorizontally),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.genre),
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 10.sp,
                        )
                    )
                }
            }
        }
    }
}

