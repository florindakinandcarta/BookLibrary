package com.example.booklibrary.ui.searchNewBook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.booklibrary.ui.theme.BookLibraryTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemSearchBook() {
    Row {
        GlideImage(
            model = "",
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(24.dp)
                .height(150.dp)
                .width(100.dp)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.reading_time),

            )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = stringResource(id = R.string.book_name),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(id = R.string.author_name),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 14.sp,
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewItemSearchBook() {
    BookLibraryTheme {
        ItemSearchBook()
    }
}