package com.example.booklibrary.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import com.example.booklibrary.data.Book

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemHomeBook(
    book: Book,
    modifier: Modifier,
    onClickedBook: (Book) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Column(
            modifier = modifier
                .padding(6.dp)
                .width(100.dp)
                .height(200.dp)
                .clickable {
                    onClickedBook(book)
                }
        ) {
            GlideImage(
                model = "",
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .height(95.dp)
                    .width(70.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                loading = placeholder(R.drawable.reading_time)
            )
            Text(
                text = book.language.toString(),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = book.genre.toString(),
                modifier = Modifier
                    .padding(6.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 12.sp,
                )
            )
        }
    }
}
