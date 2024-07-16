package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var text by remember {
            mutableStateOf("")
        }
        val emailRegex = Regex("[a-zA-Z.]+@kinandcarta.com")
        var isError by remember { mutableStateOf(false) }
        Image(
            painter = painterResource(id = R.drawable.reading_time),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.welcome_text),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                text = it
                isError = !emailRegex.matches(it)
            },
            label = {
                Text(
                    text =
                    if (isError) {
                        "Use your work email ...@kinandcarta.com"
                    } else {
                        stringResource(id = R.string.enter_email)
                    },
                    style = TextStyle(color = if (isError) Color.Red else Color.Unspecified, fontSize = 17.sp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black)
        )
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                text = it
            },
            label = {
                Text(
                    stringResource(id = R.string.enter_password),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black)
        )
        Text(
            text = AnnotatedString(stringResource(id = R.string.forgot_password)),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = 24.dp,
                    bottom = 16.dp
                )
                .clickable {
                    onForgotPasswordClick()
                },
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = stringResource(id = R.string.dont_have_acc_register),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp
                    )
            )
            Text(
                text = stringResource(id = R.string.register),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 24.dp,
                        bottom = 16.dp
                    )
                    .clickable {
                        onRegisterClick()
                    }
            )

        }
        Button(
            onClick = {
                onLoginClick()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .width(300.dp)
                .height(60.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    BookLibraryTheme {
        LoginScreen(onLoginClick = {}, onRegisterClick = {}, onForgotPasswordClick = {})
    }
}