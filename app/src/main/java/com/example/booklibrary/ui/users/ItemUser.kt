package com.example.booklibrary.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.User
import com.example.booklibrary.ui.theme.BookLibraryTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemUser(
    user: User,
    onDeleteUser: (User) -> Unit,
    onChangeRole: (User) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        GlideImage(
            model = "",
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
                .height(150.dp)
                .width(100.dp),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.reading_time)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
        ) {
            Text(
                text = user.name.toString(),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 12.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = user.email.toString(),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = user.role.toString(),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 12.sp,
                ),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
        ) {
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp),
                onClick = {
                    onDeleteUser(user)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp),
                onClick = {
                    onChangeRole(user)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }
        }

    }
}