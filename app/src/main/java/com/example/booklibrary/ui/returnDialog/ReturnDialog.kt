package com.example.booklibrary.ui.returnDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.booklibrary.R
import com.example.booklibrary.data.SampleData

@Composable
fun ReturnDialog(onNext: (String) -> Unit) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("Return type") }
    Dialog(onDismissRequest = { }) {
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
            Column {
                SampleData.returnStatus.forEach { returnStatus ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .selectable(
                                selected = (returnStatus == selectedOption),
                                onClick = {
                                    onOptionSelected(returnStatus)
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (returnStatus == selectedOption),
                            onClick = { onOptionSelected(returnStatus) }
                        )
                        Text(
                            text = returnStatus,
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.next),
                    style = TextStyle(
                        color = if (selectedOption != "Return type") MaterialTheme.colorScheme.primary else Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(
                            24.dp
                        )
                        .align(Alignment.End)
                        .then(
                            if (selectedOption != "Return type") {
                                Modifier.clickable { onNext(selectedOption) }
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReturnDialog() {
    ReturnDialog(onNext = {})
}