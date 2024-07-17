package com.example.booklibrary.ui.userProfile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.validateEmail
import com.example.booklibrary.viewModels.AuthViewModel

@Composable
fun ForgotPasswordScreen(
    onSendEmailClick: () -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val messageResponse by authViewModel.message.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(messageResponse) {
        when (messageResponse) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    (messageResponse as Resource.Success<String>).data.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                onSendEmailClick()
            }

            is Resource.Error -> {
                Toast.makeText(
                    context, (messageResponse as Resource.Error<String>).data.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {

            }
        }
    }
    Scaffold {
        it
        var isEmailValid by remember { mutableStateOf(false) }
        var isError by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            var emailInput by remember {
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
                value = emailInput,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { email ->
                    emailInput = email
                    isEmailValid =
                        validateEmail(email)
                    isError = email.isNotEmpty()
                },
                label = {
                    Text(
                        text =
                        if (isEmailValid) {
                            "Use your work email!"
                        } else {
                            stringResource(id = R.string.enter_email)
                        },
                        style = TextStyle(
                            color = if (isEmailValid) Color.Red else Color.Unspecified,
                            fontSize = 17.sp
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    authViewModel.sendPasswordResetEmail(emailInput)
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
}