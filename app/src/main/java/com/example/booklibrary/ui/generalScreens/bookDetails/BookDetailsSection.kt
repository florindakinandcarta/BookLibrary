package com.example.booklibrary.ui.generalScreens.bookDetails

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.ui.RatingBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookDetailsSection(book: Book) {
    Column {
        GlideImage(
            model = book.image,
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .height(250.dp)
                .width(200.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.reading_time)
        )
        Text(
            text = book.title,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        book.authorDTOs.forEach { author ->
            Text(
                text = author.fullName,
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
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                book.ratingFromWeb?.let { RatingBar(rating = it, onRatingChanged = {}) }
            }
            Text(
                text = book.ratingFromWeb.toString(),
                modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                ),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = book.totalPages.toString(),
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
            Column {
                Text(
                    text = book.language,
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
            Column {
                Text(
                    text = book.genres.joinToString(", "),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                )
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