package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun RegisterScreen(onLoginClick: () -> Unit, onSignUpClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),

        ) {
        var text by remember {
            mutableStateOf("")
        }
        Spacer(Modifier.size(32.dp))
        Text(
            text = stringResource(id = R.string.create_account),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp),
            style = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Text(
            text = stringResource(id = R.string.enter_info),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(12.dp),
            onValueChange = {
                text = it
            },
            label = {
                Text(
                    stringResource(id = R.string.name),
                    style = TextStyle(
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
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
                .padding(vertical = 8.dp),
            singleLine = true
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
                    style = TextStyle(
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true,
        )
        Spacer(Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.already_have_acc),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(
                        top = 52.dp,
                        bottom = 24.dp
                    )
            )
            Text(
                text = stringResource(id = R.string.login),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 52.dp,
                        bottom = 24.dp
                    )
                    .clickable {
                        onLoginClick()
                    }
            )
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
               onSignUpClick()
            },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    BookLibraryTheme {
        RegisterScreen(onLoginClick = {}, onSignUpClick = {})
    }
}