package com.example.booklibrary.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.ItemContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.viewModels.OfficeViewModel
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.showToast
import com.example.booklibrary.util.validateEmail
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    viewModel: OfficeViewModel = hiltViewModel()
) {
    val authViewModel: UserViewModel = hiltViewModel()
    val messageResponse by authViewModel.userWithRole.collectAsState()
    val context = LocalContext.current
    val offices = viewModel.offices.collectAsState().value
    val scope = rememberCoroutineScope()

    LaunchedEffect(messageResponse) {
        when (messageResponse) {
            is Resource.Success -> {
                context.showToast("User ${messageResponse.data?.fullName} successfully created.")
                onLoginClick()
            }

            is Resource.Error -> {
                context.showToast(messageResponse.message.toString())
            }

            else -> {

            }
        }
    }
    Scaffold { paddingValues ->
        paddingValues
        var name by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var confirmPassword by remember {
            mutableStateOf("")
        }
        var officeName by remember {
            mutableStateOf("")
        }
        var showPassword by remember { mutableStateOf(false) }
        val passwordVisualTransformation = remember { PasswordVisualTransformation() }
        var isError by remember { mutableStateOf(false) }
        var isEmailValid by remember { mutableStateOf(false) }
        var expanded by remember {
            mutableStateOf(false)
        }
        var text by remember {
            mutableStateOf(SampleData.countries[0])
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(insets = WindowInsets.ime.union(WindowInsets.navigationBars))
        ) {
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
                value = name,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    name = it
                    isError = it.isNotEmpty()
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
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = text,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    label = {
                        Text(
                            text = stringResource(id = R.string.choose_office)
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = TextFieldDefaults.colors(focusedTextColor = Color.Black)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.exposedDropdownSize()
                ) {
                    if (offices is Resource.Success) {
                        offices.data?.let { offices ->
                            offices.forEach { office ->
                                office.name?.let { officeNameSelected ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = officeNameSelected,
                                            )
                                        },
                                        onClick = {
                                            text = officeNameSelected
                                            officeName = officeNameSelected
                                            expanded = false
                                        },
                                        contentPadding = ItemContentPadding,
                                        colors = MenuDefaults.itemColors(textColor = Color.Black)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            OutlinedTextField(
                value = email,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    email = it
                    isEmailValid =
                        validateEmail(email)
                    isError = it.isNotEmpty()
                },
                label = {
                    Text(
                        text =
                        if (isEmailValid) {
                            stringResource(id = R.string.use_work_email)
                        } else {
                            stringResource(id = R.string.enter_email)
                        },
                        style = TextStyle(
                            color = if (isEmailValid) Color.Red else Color.Gray,
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
                value = password,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    password = it
                    isError = it.isNotEmpty()
                },
                label = {
                    Text(
                        text = if (password.length < 6) {
                            stringResource(id = R.string.password_min_char)
                        } else {
                            stringResource(id = R.string.enter_password)
                        },
                        style = TextStyle(
                            color = Color.Gray
                        )
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
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
                },
            )
            OutlinedTextField(
                value = confirmPassword,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    confirmPassword = it
                    isError = it.isNotEmpty()
                },
                label = {
                    Text(
                        stringResource(id = R.string.confirm_password),
                        style = TextStyle(
                            color = Color.Gray
                        )
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
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
                },
                isError = password != confirmPassword
            )
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
                    val userRegisterRequest = UserRegistrationRequest(
                        name,
                        email,
                        "Skopje",
                        password
                    )
                    scope.launch {
                        authViewModel.registerUser(userRegisterRequest)
                    }
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(32.dp),
                enabled = isError
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
}