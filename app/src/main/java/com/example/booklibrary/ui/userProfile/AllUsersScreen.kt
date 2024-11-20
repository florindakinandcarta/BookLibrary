package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.util.Resource

@Composable
fun AllUsersScreen(
    onBackClicked: () -> Unit,
    onChangeRoleClicked: (UserUpdateRoleRequest) -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val listOfUsers = userViewModel.users.collectAsState().value
    Scaffold(
        topBar = {
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxHeight()
        ) {
            when (listOfUsers) {
                is Resource.Success -> {
                    listOfUsers.data?.let { users ->
                        items(users) { user ->
                            ItemUser(
                                user = user,
                                onBackClicked = onBackClicked,
                                onChangeRoleClicked = onChangeRoleClicked
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    listOfUsers.message?.let { error ->
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = error.toString(),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }
                }

                is Resource.Loading -> {

                }
            }
        }
    }
}