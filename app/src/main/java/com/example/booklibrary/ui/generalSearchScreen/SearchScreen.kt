package com.example.booklibrary.ui.generalSearchScreen

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.ui.ItemBook
import com.example.booklibrary.ui.home.SearchViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onScanClick: () -> Unit,
    onBackClicked: () -> Unit,
    onClickedBook: (Book) -> Unit,
) {
    val searchViewModel: SearchViewModel = viewModel()
    val searchText by searchViewModel.searchText.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()
    val booksList by searchViewModel.booksList.collectAsState()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onScanClick()
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        paddingValues
        if (isSearching) {
            BackHandler {
                searchViewModel.onToggleSearch()
            }
        }
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = searchText,
            onQueryChange = {
                searchViewModel.onSearchTextChange(it)
            },
            onSearch = {
                searchViewModel.onSearchTextChange(it)
                keyboardController?.hide()
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .size(38.dp)
                        .padding(end = 8.dp),
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
            },
            active = isSearching,
            onActiveChange = {
                searchViewModel.onToggleSearch()
            },
            trailingIcon = {
                if (isSearching || searchText.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable {
                                searchViewModel.onToggleSearch()
                            },
                        style = TextStyle(color = Color.DarkGray)
                    )
                } else {
                    IconButton(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(32.dp),
                        onClick = {
                            when {
                                cameraPermissionState.status.isGranted -> {
                                    onScanClick()
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
                                                Uri.fromParts(
                                                    "package",
                                                    context.packageName,
                                                    null
                                                )
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
                            imageVector = ImageVector.vectorResource(R.drawable.qr_code),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    stringResource(id = R.string.search),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
                dividerColor = Color.Black,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black
                )
            ),
        ) {
            LazyColumn {
                items(booksList) { book ->
                    ItemBook(book = book, onClickedBook = onClickedBook)
                }
            }
        }
    }
}
