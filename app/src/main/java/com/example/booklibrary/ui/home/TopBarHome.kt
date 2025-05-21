package com.example.booklibrary.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.viewModels.UserViewModel

@Composable
fun TopBarHome(
    onNotificationClick: () -> Unit,
    onSearchClick: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val userInfo = userViewModel.userInfo.collectAsState().value
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = "Hey, ${userInfo.data?.fullName}",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 6.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Text(
                text = stringResource(id = R.string.start_reading),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                )
            )
        }
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
                onNotificationClick()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

