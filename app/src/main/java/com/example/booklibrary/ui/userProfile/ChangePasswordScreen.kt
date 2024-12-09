package com.example.booklibrary.ui.userProfile

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel

@Composable
fun ChangePasswordScreen(
    onBackClicked: () -> Unit,
    onChangePasswordRequest: (UserChangePasswordRequest) -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userInfo = userViewModel.userInfo.collectAsState().value
    var isPasswordValid by remember {
        mutableStateOf(false)
    }
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualTransformation = remember { PasswordVisualTransformation() }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                Text(
                    text = stringResource(id = R.string.change_password),
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                Spacer(Modifier.weight(1f))

            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = paddingValues.calculateTopPadding()
                )
        ) {
            Text(
                text = stringResource(id = R.string.change_password_info),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
            OutlinedTextField(
                value = oldPassword,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { password ->
                    oldPassword = password
                    isPasswordValid = password.isNotEmpty()
                },
                label = {
                    Text(
                        stringResource(id = R.string.enter_old_password),
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
            OutlinedTextField(
                value = newPassword,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { password ->
                    newPassword = password
                    isPasswordValid = password.isNotEmpty()
                },
                label = {
                    Text(
                        stringResource(id = R.string.enter_new_password),
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
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    if (oldPassword.isBlank() || newPassword.isBlank()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.all_fields_required),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val newPasswordRequest = userInfo.data?.userId?.let { userId ->
                            UserChangePasswordRequest(
                                userId,
                                oldPassword,
                                newPassword
                            )
                        }
                        newPasswordRequest?.let { onChangePasswordRequest(it) }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .width(300.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.change_password),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}