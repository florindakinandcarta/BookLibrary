package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun ForgotPasswordScreen(
    onSendEmailClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        var text by remember {
            mutableStateOf("")
        }
        Spacer(Modifier.size(32.dp))

        Text(
            text = stringResource(id = R.string.forgot_password),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Text(
            text = stringResource(id = R.string.enter_email_forgot),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                text = it
            },
            label = {
                Text(
                    stringResource(id = R.string.enter_email),
                    style = TextStyle(
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onSendEmailClick()
            },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.send_email),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForgotPassword() {
    BookLibraryTheme {
        ForgotPasswordScreen(onSendEmailClick = {})
    }
}