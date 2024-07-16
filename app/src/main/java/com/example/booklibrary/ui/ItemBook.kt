package com.example.booklibrary.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.Book

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemBook(
    book: Book,
    onClickedBook: (Book) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClickedBook(book)
            }
    ) {
        book.let {
            GlideImage(
                model ="",
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(16.dp)
                    .height(90.dp)
                    .width(65.dp),
                contentScale = ContentScale.Crop,
                loading = placeholder(R.drawable.reading_time)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
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
                Text(
                    text = book.language.toString(),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = 10.sp,
                    ),
                )
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
                    Text(
                        text = stringResource(id = R.string.rating),
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