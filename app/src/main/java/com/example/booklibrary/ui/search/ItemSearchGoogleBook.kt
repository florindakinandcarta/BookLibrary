package com.example.booklibrary.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.googleBooks.VolumeInfo


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemSearchGoogleBook(
    book: VolumeInfo,
    onBookClicked: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                book.industryIdentifiers[0].identifier?.let { isbn ->
                    onBookClicked(isbn)
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = book.imageLinks?.smallThumbnail,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .height(110.dp)
                .width(85.dp),
            contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.reading_time)
        )
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            book.industryIdentifiers.firstOrNull()?.identifier.let { isbn ->
                Text(
                    text = isbn ?: "",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = 8.sp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                )
            }
            book.title?.let { title ->
                Text(
                    text = title,
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
                text = book.language.toString(),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 10.sp,
                ),
            )
        }
    }
}