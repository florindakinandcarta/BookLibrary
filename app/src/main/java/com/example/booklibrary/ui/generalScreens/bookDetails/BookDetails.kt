package com.example.booklibrary.ui.generalScreens.bookDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.RateReview
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.ui.review.ItemUserReview
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun BookDetails(
    bookISBN: String,
    onBackClicked: () -> Unit,
    onAddReviewClicked: () -> Unit
) {
    Scaffold(topBar = {
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
    }) { paddingValues ->
        SampleData.books.let { book ->
            LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                item {
                    BookDetailsSection(book[1])
                }
                item {
                    AboutBook(book[1])
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(id = R.string.user_reviews),
                            modifier = Modifier
                                .padding(20.dp)
                                .clickable {
                                    onAddReviewClicked()
                                },
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                        Spacer(Modifier.weight(1f))
                        IconButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp),
                            onClick = {
                                onAddReviewClicked()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.RateReview,
                                contentDescription = null,
                            )
                        }
                    }
                }
                items(3) {
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
                                /*TODO()*/
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(32.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.borrow),
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
}

@Preview(showBackground = true)
@Composable 
fun PreviewBookDetails(){
    BookLibraryTheme {
        BookDetails(bookISBN = "", onBackClicked = { /*TODO*/ }) {
            
        }
    }
}