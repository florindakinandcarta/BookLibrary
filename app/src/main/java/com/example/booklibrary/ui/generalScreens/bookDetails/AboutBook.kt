package com.example.booklibrary.ui.generalScreens.bookDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.Book

@Composable
fun AboutBook(book: Book) {
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
        Text(
            text = book.description,
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
        if (showReadMoreButtonState) {
            Text(
                text = if (expandedState) stringResource(id = R.string.read_less) else stringResource(
                    id = R.string.read_more
                ),

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