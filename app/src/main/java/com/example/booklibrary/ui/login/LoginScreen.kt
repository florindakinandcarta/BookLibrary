package com.example.booklibrary.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.request.UserLoginRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.showToast
import com.example.booklibrary.util.validateEmail

@Composable
fun LoginScreen(
    onLoginClick: (UserLoginRequest) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val userJWTToken = userViewModel.userJWTToken.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(userJWTToken) {
       when(userJWTToken){
           is Resource.Loading -> {
           }
           is Resource.Error -> {
               context.showToast(userJWTToken.message.toString())
           }
           is Resource.Success -> {

           }
       }
    }
    Scaffold {
        it
        var emailInput by remember {
            mutableStateOf("")
        }
        var passwordInput by remember {
            mutableStateOf("")
        }
        var isEmailValid by remember { mutableStateOf(false) }
        var isError by remember {
            mutableStateOf(false)
        }
        var isPasswordValid by remember {
            mutableStateOf(false)
        }
        var showPassword by remember { mutableStateOf(false) }
        val passwordVisualTransformation = remember { PasswordVisualTransformation() }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(insets = WindowInsets.ime.union(WindowInsets.navigationBars))
        ) {
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
            OutlinedTextField(
                value = passwordInput,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { password ->
                    passwordInput = password
                    isPasswordValid = password.isNotEmpty()
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
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    passwordVisualTransformation
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Icon(
                        if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = stringResource(id = R.string.password_visibility_des),
                        modifier = Modifier.clickable { showPassword = !showPassword }
                    )
                }
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
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    val userLoginRequest = UserLoginRequest(
                        emailInput,
                        passwordInput
                    )
                    onLoginClick(userLoginRequest)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .width(300.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(32.dp),
                enabled = isError && isPasswordValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                )
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
}