package com.example.booklibrary.ui.review

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.booklibrary.R
import com.example.booklibrary.ui.RatingBar

@Composable
fun UserReviewDialog(
    onDismissDialog: () -> Unit,
    onSubmitClick: () -> Unit
) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    Dialog(onDismissRequest = { onDismissDialog() }) {
        ElevatedCard(
            onClick = { },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            when (currentStep) {
                1 -> StepOneDialog(onNext = { currentStep = 2 }, onCancel = { onDismissDialog() })
                2 -> StepTwoDialog(
                    onPrevious = { currentStep = 1 },
                    onSubmit = { onSubmitClick() })
            }
        }
    }
}

@Composable
fun StepOneDialog(onCancel: () -> Unit, onNext: () -> Unit) {
    var rating by remember {
        mutableDoubleStateOf(0.0)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            RatingBar(rating = rating, onRatingChanged = {
                rating = it
            })
        }
        Row {
            Text(
                text = stringResource(id = R.string.cancel),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        start = 24.dp
                    )
                    .clickable {
                        onCancel()
                    },
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.next),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        end = 24.dp
                    )
                    .clickable {
                        onNext()
                    }
            )
        }

    }
}

@Composable
fun StepTwoDialog(onPrevious: () -> Unit, onSubmit: () -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                text = it
            },
            placeholder = {
                Text(
                    stringResource(id = R.string.recommend_book),
                    style = TextStyle(
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            maxLines = 3,
            singleLine = false
        )
        Row {
            Text(
                text = stringResource(id = R.string.previous),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        start = 24.dp
                    )
                    .clickable { onPrevious() },
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.submit),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        end = 24.dp
                    )
                    .clickable {
                        onSubmit()
                    },
            )
        }
    }
}
