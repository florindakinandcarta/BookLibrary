package com.example.booklibrary.ui.requested

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.ItemContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.Book
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.ui.bookDetails.AboutBook
import com.example.booklibrary.ui.bookDetails.BookDetailsSection
import com.example.booklibrary.ui.review.ItemUserReview
import com.example.booklibrary.data.book.viewModels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestedBookDetails(
    book: Book,
    onBackClicked: () -> Unit,
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isUserAdmin by authViewModel.userAdmin.collectAsState()
    var expanded by remember {
        mutableStateOf(false)
    }
    var bookStatus by remember {
        mutableStateOf(SampleData.bookStatus[0])
    }
    Scaffold(topBar = {
        Row {
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
            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp),
                onClick = {
                    onBackClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Recommend,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }) { paddingValues ->
        book.let { bookItem ->
            LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                item {
                    BookDetailsSection(bookItem)
                }
                item {
//                    AboutBook()
                    if (!isUserAdmin) {
                        Text(
                            text = stringResource(id = R.string.user_reviews),
                            modifier = Modifier
                                .padding(20.dp),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                    } else {
                        Row(){
                            Text(
                                text = stringResource(id = R.string.number_of_likes),
                                modifier = Modifier
                                    .padding(20.dp),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                            Text(
                                text = book.numberOfLikes.toString(),
                                modifier = Modifier
                                    .padding(vertical = 20.dp),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                ),
                            )
                        }
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            modifier = Modifier
                                .padding(12.dp)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                value = bookStatus,
                                onValueChange = {},
                                readOnly = true,
                                singleLine = true,
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.change_status)
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
                                SampleData.bookStatus.forEach { status ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = status,
                                            )
                                        },
                                        onClick = {
                                            expanded = false
                                            bookStatus = status
                                        },
                                        contentPadding = ItemContentPadding,
                                        colors = MenuDefaults.itemColors(textColor = Color.Black)
                                    )
                                }
                            }
                        }
                        Button(
                            onClick = {
                                /*TODO() CHANGE THE STATUS OF THE BOOK*/
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(32.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.change_status),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    }
                }
                if (!isUserAdmin) {
                    items(3) {
                        ItemUserReview()
                    }
                }
            }
        }
    }
}