package com.example.booklibrary.ui.requested

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.booklibrary.R
import com.example.booklibrary.data.models.BookStatus
import com.example.booklibrary.data.models.RequestedBook
import com.example.booklibrary.data.models.displayName
import com.example.booklibrary.data.models.request.BookChangeStatus
import com.example.booklibrary.viewModels.UserViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemRequestedBook(
    book: RequestedBook,
    onLikeBook: (String) -> Unit,
    onClickedBook: (String) -> Unit,
    onChangeStatusClicked: (BookChangeStatus) -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var isLiked by remember { mutableStateOf(false) }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedStatusFilter by remember {
        mutableStateOf("")
    }
    val isUserAdmin = userViewModel.isUserAdminFlow.collectAsState(initial = false)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .wrapContentSize()
                .clickable {
                    book.bookISBN?.let { isbn ->
                        onClickedBook(isbn)
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            book.let {
                GlideImage(
                    model = book.image,
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
                        .padding(start = 8.dp, top = 8.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = book.bookStatus.toString(),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 8.sp,
                        ),
                    )
                    Text(
                        text = book.title.toString(),
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.number_of_likes),
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = TextStyle(
                                fontSize = 10.sp,
                            ),
                        )
                        Text(
                            text = book.likeCounter.toString(),
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = TextStyle(
                                fontSize = 10.sp,
                            ),
                        )
                        Spacer(Modifier.weight(1f))
                    }
                    Text(
                        text = book.requestedDate.toString(),
                        modifier = Modifier
                            .padding(8.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                        ),
                    )
                }
            }
            if (isUserAdmin.value) {
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
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        BookStatus.entries.forEach { bookStatus ->
                            DropdownMenuItem(
                                text = { Text(text = bookStatus.displayName) },
                                onClick = {
                                    expanded = false
                                    selectedStatusFilter = bookStatus.name
                                    val bookChangeStatus =
                                        BookChangeStatus(book.id, selectedStatusFilter)
                                    onChangeStatusClicked(bookChangeStatus)
                                }
                            )
                        }
                    }
                }
            } else {
                IconButton(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp),
                    onClick = {
                        isLiked = !isLiked
                        expanded = !expanded
                        book.bookISBN?.let { isbn ->
                            onLikeBook(isbn)
                        }
                    },
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.ThumbUp else Icons.Filled.ThumbUpOffAlt,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}