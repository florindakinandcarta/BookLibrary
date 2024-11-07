package com.example.booklibrary.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.data.book.viewModels.BookViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComposeSwipeablePages(
    bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel(),
    bookViewModel: BookViewModel = hiltViewModel()
) {
    val bookCheckouts = bookCheckoutViewModel.bookCheckouts.collectAsState().value
    val bookDetails = bookViewModel.bookDetails.collectAsState().value
    val pagerState = rememberPagerState {
        bookCheckouts.data?.size ?: 0
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        bookCheckouts.data?.let { books ->
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.padding(8.dp)
            ) { index ->
                val book = books[index]
                LaunchedEffect(Unit) {
                    bookViewModel.getBookByISBN(book.bookISBN)
                }
                GlideImage(
                    model = bookDetails?.data?.image ?: R.drawable.reading_time,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(90.dp)
                        .width(65.dp),
                    contentScale = ContentScale.Crop,
                    loading = placeholder(R.drawable.reading_time)
                )
                Column {
                    Text(
                        text = book.bookTitle,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        "Return date: ${book.scheduledReturnDate}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            bookCheckouts.data?.forEachIndexed { index, _ ->
                val color =
                    if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = color, shape = CircleShape)
                        .padding(horizontal = 8.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
        }
    }
}