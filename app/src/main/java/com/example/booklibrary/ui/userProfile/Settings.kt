package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.viewModels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun Settings(
    onBackClicked: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
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
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.settings),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Spacer(Modifier.weight(1f))
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
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
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    stringResource(id = R.string.change_theme),
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {

                        },
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = isSystemInDarkTheme(),
                    onCheckedChange = { isChecked ->

                    }
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    scope.launch {
                        userViewModel.signOutUser()
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .width(300.dp)
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