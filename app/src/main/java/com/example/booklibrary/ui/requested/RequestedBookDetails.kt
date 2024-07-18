package com.example.booklibrary.ui.requested

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.ui.bookDetails.AboutBook
import com.example.booklibrary.ui.bookDetails.BookDetailsSection
import com.example.booklibrary.ui.review.ItemUserReview
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun RequestedBookDetails(
    book: Book,
    onBackClicked: () -> Unit,
) {
    Scaffold(topBar = {
        Row {
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
                    onBackClicked()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.recommend),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }) { paddingValues ->
        book.let { bookItem ->
            LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                item {
                    BookDetailsSection(bookItem)
                }
                item {
                    AboutBook()
                    Text(
                        text = stringResource(id = R.string.user_reviews),
                        modifier = Modifier
                            .padding(20.dp),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )

                }
                items(3) {
                    ItemUserReview()
                }
            }
        }
    }
}