package com.example.booklibrary.ui.borrowedHistory

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.models.ReturnStatus
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.Resource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun BookCheckoutsScreen(
    onBorrowedBookClick: (String) -> Unit,
    onReturnClick: () -> Unit,
    onSearchClick: () -> Unit,
    bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    onGetByStatusClicked: (String) -> Unit,
) {
    var numberOfPages by remember {
        mutableIntStateOf(0)
    }
    var pageSize by remember {
        mutableIntStateOf(5)
    }
    val isUserAdmin = userViewModel.isUserAdminFlow.collectAsState(initial = false)
    val listOfBooks = bookCheckoutViewModel.bookCheckouts.collectAsState().value
    val books = bookCheckoutViewModel.books.collectAsState().value
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val scope = rememberCoroutineScope()
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedStatusFilter by remember {
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onReturnClick()
        } else {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = context.resources.getString(R.string.camera_is_needed),
                    actionLabel = context.resources.getString(R.string.go_to_settings),
                )
                if (result == SnackbarResult.ActionPerformed) {
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.borrowed_history),
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
                Spacer(Modifier.weight(1f))
                if (isUserAdmin.value) {
                    IconButton(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp),
                        onClick = {
                            onSearchClick()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentSize(Alignment.TopEnd)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(6.dp)
                                .size(24.dp),
                            onClick = {
                                expanded = !expanded
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FilterAlt,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            ReturnStatus.entries.forEach { returnStatus ->
                                DropdownMenuItem(
                                    text = { Text(text = returnStatus.displayName) },
                                    onClick = {
                                        expanded = false
                                        selectedStatusFilter = returnStatus.name
                                        onGetByStatusClicked(returnStatus.name)
                                    }
                                )
                            }
                        }
                    }
                }
                IconButton(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp),
                    onClick = {
                        when {
                            cameraPermissionState.status.isGranted -> {
                                onReturnClick()
                            }

                            cameraPermissionState.status.shouldShowRationale -> {
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = context.resources.getString(R.string.camera_is_needed),
                                        actionLabel = context.resources.getString(R.string.go_to_settings),
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        val intent = Intent(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", context.packageName, null)
                                        )
                                        context.startActivity(intent)
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
                        imageVector = Icons.Filled.AssignmentReturned,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(bottom = 80.dp)
                .fillMaxHeight()
        ) {
            if (isUserAdmin.value) {
                item {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            bookCheckoutViewModel.getAllBookCheckouts()
                        }
                    }
                }
                when (books) {
                    is Resource.Success -> {
                        books.data?.let { bookItems ->
                            items(bookItems) { item ->
                                ItemBorrowedBooksAdmin(
                                    book = item,
                                    onBorrowedBookClick = onBorrowedBookClick
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        books.message?.let { error ->

                        }
                    }

                    is Resource.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                                color = MaterialTheme.colors.primary,
                                trackColor = MaterialTheme.colors.secondary,
                            )
                        }
                    }
                }
            } else {
                item {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            bookCheckoutViewModel.getAllBookCheckoutsForUser()
                        }
                    }
                }
                when (listOfBooks) {
                    is Resource.Success -> {
                        listOfBooks.data?.let { books ->
                            items(books) { book ->
                                ItemBorrowedBooks(
                                    book = book,
                                    onBorrowedBookClick = onBorrowedBookClick
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(64.dp),
                                    color = MaterialTheme.colors.primary,
                                    trackColor = MaterialTheme.colors.secondary,
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        listOfBooks.message?.let { error ->
                            //call the dialog pop up for error display it for 5s and dismiss it
                        }
                    }
                }
            }
        }
    }
}
