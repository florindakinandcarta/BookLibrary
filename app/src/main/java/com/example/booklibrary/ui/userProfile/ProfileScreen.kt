package com.example.booklibrary.ui.userProfile

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.convertBase64ToBitmap
import com.example.booklibrary.util.convertBitmapToString
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileScreen(
    onChangePasswordClicked: () -> Unit,
    onAllUsersClicked: () -> Unit,
    onClickUpdateUserData: (UserUpdateDataRequest) -> Unit,
    onSettingsClicked: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val isUserAdmin = userViewModel.isUserAdminFlow.collectAsState(initial = false)
    val userInfo = userViewModel.userInfo.collectAsState().value
    var isEditMode by remember {
        mutableStateOf(false)
    }
    var displayName by remember { mutableStateOf(userInfo.data?.fullName ?: "") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val takePictureLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                val encodedPhoto = convertBitmapToString(it)
                val updateUserData = UserUpdateDataRequest(image = encodedPhoto)
                onClickUpdateUserData(updateUserData)
            }
        }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            takePictureLauncher.launch()
        } else {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = context.resources.getString(R.string.camera_is_needed),
                    actionLabel = context.resources.getString(R.string.go_to_settings),
                    withDismissAction = true
                )
                if (result == SnackbarResult.ActionPerformed) {
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    val packageManager = context.packageManager
                    if (intent.resolveActivity(packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.resources.getString(R.string.something_went_wrong),
                            )
                        }
                    }
                } else {
                    snackbarHostState.showSnackbar(
                        message = context.resources.getString(R.string.cant_scan)
                    )
                }
            }
        }
    }
    LaunchedEffect(userInfo) {
        displayName = userInfo.data?.fullName ?: ""
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                        onSettingsClicked()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.padding(bottom = 80.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
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
                            when {
                                cameraPermissionState.status.isGranted -> {
                                    takePictureLauncher.launch()
                                }

                                cameraPermissionState.status.shouldShowRationale -> {
                                    scope.launch {
                                        val result = snackbarHostState.showSnackbar(
                                            message = context.resources.getString(R.string.camera_is_needed),
                                            actionLabel = context.resources.getString(R.string.go_to_settings),
                                            withDismissAction = true
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            val intent = Intent(
                                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", context.packageName, null)
                                            )
                                            val packageManager = context.packageManager
                                            if (intent.resolveActivity(packageManager) != null) {
                                                context.startActivity(intent)
                                            } else {
                                                scope.launch {
                                                    snackbarHostState.showSnackbar(
                                                        message = context.resources.getString(R.string.something_went_wrong),
                                                    )
                                                }
                                            }
                                        } else {
                                            snackbarHostState.showSnackbar(
                                                message = context.resources.getString(R.string.cant_scan)
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
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
                modifier = Modifier
                    .padding(
                        start = 32.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .clickable {
                        onChangePasswordClicked()
                    }
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
                        .align(Alignment.CenterVertically),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
            if (isUserAdmin.value) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(
                            start = 32.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                        .clickable {
                            onAllUsersClicked()
                        }
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
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )

                }
            }
        }
    }
}