package com.example.booklibrary.ui.userProfile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.convertBase64ToBitmap
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    onChangePasswordClicked: () -> Unit,
    onAllUsersClicked: () -> Unit,
    onClickUpdateUserData: (UserUpdateDataRequest) -> Unit,
    onChangeProfilePhotoClicked: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val isUserAdmin = userViewModel.isUserAdminFlow.collectAsState(initial = false)
    val userInfo = userViewModel.userInfo.collectAsState().value
    val scope = rememberCoroutineScope()
    var isEditMode by remember {
        mutableStateOf(false)
    }
    var displayName by remember { mutableStateOf(userInfo.data?.fullName ?: "") }
    LaunchedEffect(userInfo) {
        displayName = userInfo.data?.fullName ?: ""
    }
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.profile),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .padding(6.dp)
                    .size(24.dp),
                onClick = {
                    isEditMode = !isEditMode
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier.size(200.dp)
                ) {

                    val bitMap = convertBase64ToBitmap(userInfo.data?.profilePicture)

                    bitMap?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(200.dp)

                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(60.dp)
                            .padding(end = 16.dp, top = 16.dp),
                        onClick = {
                            onChangeProfilePhotoClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp),
                            tint = Color(0xFF6200EE)
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
            }
            if (isEditMode) {
                OutlinedTextField(
                    value = displayName,
                    shape = RoundedCornerShape(12.dp),
                    onValueChange = {
                        displayName = it
                    },
                    label = {
                        Text(
                            stringResource(id = R.string.name),
                            style = TextStyle(
                                Color.Unspecified
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                val updateUserData = UserUpdateDataRequest(fullName = displayName)
                                onClickUpdateUserData(updateUserData)
                                isEditMode = false
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Save",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                )
            } else {
                Text(
                    text = userInfo.data?.fullName.toString(),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(
                    start = 32.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = userInfo.data?.email.toString(),
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .align(Alignment.CenterVertically),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(
                    start = 32.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    stringResource(id = R.string.change_password),
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onChangePasswordClicked()
                        },
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        onChangePasswordClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }
            if (isUserAdmin.value) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(
                        start = 32.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        stringResource(id = R.string.all_users),
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                onAllUsersClicked()
                            },
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = {
                            onAllUsersClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    scope.launch {
                        userViewModel.signOutUser()
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 120.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.log_out),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}