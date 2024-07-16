package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.ui.theme.BookLibraryTheme

@Composable
fun ProfileScreen() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.profile),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier.size(200.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_pic),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)

                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(60.dp)
                        .padding(end = 16.dp, top = 16.dp),
                    onClick = {
                        TODO()
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
        }
        Text(
            text = stringResource(id = R.string.florinda_hasani),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
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
                stringResource(id = R.string.email),
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
                imageVector = ImageVector.vectorResource(R.drawable.shelves),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                stringResource(id = R.string.shelve),
                modifier = Modifier
                    .padding(start = 24.dp)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = {
                    TODO()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                TODO()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .width(300.dp)
                .height(60.dp),
            shape = RoundedCornerShape(12.dp)
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


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    BookLibraryTheme {
        ProfileScreen()
    }
}