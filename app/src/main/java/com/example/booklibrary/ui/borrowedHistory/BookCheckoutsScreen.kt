package com.example.booklibrary.ui.borrowedHistory

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.Search
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
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.viewModels.AuthViewModel
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.ui.ItemBook
import com.example.booklibrary.util.Resource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BookCheckoutsScreen(
    onBorrowedBookClick: (Book) -> Unit,
    onReturnClick: () -> Unit,
    onSearchClick: () -> Unit,
    viewModel: BookCheckoutViewModel = hiltViewModel(),
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    var numberOfPages by remember {
        mutableIntStateOf(0)
    }
    var pageSize by remember {
        mutableIntStateOf(5)
    }
    val isUserAdmin by authViewModel.userAdmin.collectAsState()
//    val listOfBooks = viewModel.books.collectAsState().value
    var swipedDown by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(swipedDown) {
        if (isUserAdmin) {
//            viewModel.getAllBookCheckoutsPaginated(
//                numberOfPages,
//                pageSize
//            )
        } else {
//            viewModel.getAllBookCheckouts()
        }
    }
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val scope = rememberCoroutineScope()
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
//        BorrowedBooksList(paddingValues, onBorrowedBookClick, listOfBooks)
    }
}

@Composable
fun BorrowedBooksList(
    paddingValues: PaddingValues,
    onBorrowedBookClick: (Book) -> Unit,
    listOfBooks: Resource<List<BookCheckoutWithUserAndBookItemResponse>>
) {
    LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        when (listOfBooks) {
            is Resource.Success -> {
                listOfBooks.data?.let { books ->
                    items(books) { book ->
//                        ItemBorrowedBooks(
//                            book = book,
//                            onBorrowedBookClick = onBorrowedBookClick
//                        )
                    }
                }
            }

            is Resource.Loading -> {
                //loader display
            }

            is Resource.Error -> {
                listOfBooks.data?.let { error ->
                    //call the dialog pop up for error display it for 5s and dismiss it
                }
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewBorrowedBooksScreen() {
//    BookLibraryTheme {
//        BookCheckoutsScreen(
//            onBorrowedBookClick = {},
//            onReturnClick = { /*TODO*/ },
//            onSearchClick = { /*TODO*/ })
//    }
//}