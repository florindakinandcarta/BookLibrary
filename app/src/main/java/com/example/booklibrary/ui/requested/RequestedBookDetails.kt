package com.example.booklibrary.ui.requested

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
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
import com.example.booklibrary.data.googleBooks.VolumeInfo
import com.example.booklibrary.ui.RatingBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RequestedBookDetails(book: VolumeInfo, onRequestClick: () -> Unit, onBackClick: () -> Unit) {
    Scaffold(topBar = {
        IconButton(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp),
            onClick = {
                onBackClick()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            item {
                Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                    GlideImage(
                        model = book.imageLinks?.smallThumbnail,
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
                        text = book.title!!,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    Text(
                        text = book.authors.joinToString(", "),
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {

                            },
                        style = TextStyle(
                            fontSize = 14.sp,
                        ),
                    )
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
                            RatingBar(rating = 3.8, onRatingChanged = {})
                        }
                        Text(
                            text = "3.8",
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
                                text = book.pageCount.toString(),
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
                                text = book.language!!,
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
                                text = book.categories.joinToString(", "),
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
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp)
                ) {
                    val minimumLineLength = 3
                    var expandedState by remember {
                        mutableStateOf(false)
                    }
                    var showReadMoreButtonState by remember {
                        mutableStateOf(false)
                    }
                    val maxLines = if (expandedState) 200 else minimumLineLength
                    Text(
                        text = stringResource(id = R.string.about),
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    book.description?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally),
                            style = TextStyle(
                                fontSize = 14.sp,
                                textAlign = TextAlign.Justify
                            ),
                            lineHeight = 25.sp,
                            maxLines = maxLines,
                            overflow = TextOverflow.Ellipsis,
                            onTextLayout = { textLayoutResult: TextLayoutResult ->
                                if (textLayoutResult.lineCount > minimumLineLength - 1) {
                                    if (textLayoutResult.isLineEllipsized(minimumLineLength - 1)) showReadMoreButtonState =
                                        true
                                }
                            }
                        )
                    }
                    if (showReadMoreButtonState) {
                        Text(
                            text = if (expandedState) stringResource(id = R.string.read_less) else stringResource(
                                id = R.string.read_more
                            ),
                            color = MaterialTheme.colorScheme.secondary,

                            modifier = Modifier
                                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                                .clickable {
                                    expandedState = !expandedState
                                },

                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onRequestClick()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .width(300.dp)
                            .height(60.dp),
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.request_book),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                }
            }
        }
    }
}