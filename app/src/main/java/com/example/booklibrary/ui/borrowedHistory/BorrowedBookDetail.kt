package com.example.booklibrary.ui.borrowedHistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.ui.bookDetails.AboutBook
import com.example.booklibrary.ui.bookDetails.BookDetailsSection
import com.example.booklibrary.ui.review.ItemUserReview

@Composable
fun BorrowedBookDetail(
    book: Book,
    onBackClicked: () -> Unit,
    onEditReviewClicked: () -> Unit,
    onReportAsLostClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            Row() {
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp),
                    onClick = {
                        onBackClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp),
                    onClick = {
                        onReportAsLostClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Report,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            item {
                BookDetailsSection(book)
            }
            item {
                AboutBook()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.my_review),
                        modifier = Modifier
                            .padding(20.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(32.dp),
                        onClick = {
                            onEditReviewClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            item {
                ItemUserReview()
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            TODO()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .width(300.dp)
                            .height(60.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.borrow_again),
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