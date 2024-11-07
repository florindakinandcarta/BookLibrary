package com.example.booklibrary.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.showToast
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    onBackClicked: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
//    val user by userViewModel.user.collectAsState()
//    val response by userViewModel.response.collectAsState()
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

//    LaunchedEffect(response) {
//        when(response){
//            is Resource.Success -> {
//                context.showToast((response as Resource.Success<String>).data.toString())
//                onBackClicked()
//            }
//            is Resource.Error -> {
//                context.showToast(context.getString(R.string.something_went_wrong))
//            }
//            else -> {
//            }
//        }
//    }

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
                onValueChange = {
                    oldPassword = it
                },
                label = {
                    Text(
                        stringResource(id = R.string.enter_old_password),
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
                value = newPassword,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    newPassword = it
                },
                label = {
                    Text(
                        stringResource(id = R.string.enter_new_password),
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
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    if (oldPassword.isBlank()|| newPassword.isBlank()){
                        Toast.makeText(context, context.getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show()
                    }else {
//                        val newPasswordRequest = UserChangePasswordRequest(
//                            user.data?.userId!!,
//                            oldPassword,
//                        )
                        scope.launch {
//                            userViewModel.changePassword(newPasswordRequest)
                        }
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
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