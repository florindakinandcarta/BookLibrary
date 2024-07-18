package com.example.booklibrary.ui.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.ui.RatingBar
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun ItemUserReview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight()
            .shadow(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.florinda_hasani),
                modifier = Modifier
                    .padding(8.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.weight(1f))
            Box(modifier = Modifier
                .padding(top = 2.dp)
                .height(30.dp)) {
                RatingBar(rating = 3.8, onRatingChanged = {})
            }
        }
        Text(
            text = stringResource(id = R.string.dumb_text),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            ),
            lineHeight = 25.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}