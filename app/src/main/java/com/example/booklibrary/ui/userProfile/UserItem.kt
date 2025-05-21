package com.example.booklibrary.ui.userProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.R
import com.example.booklibrary.data.models.UserRole
import com.example.booklibrary.data.models.displayName
import com.example.booklibrary.data.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.models.response.UserWithRoleResponse

@Composable
fun ItemUser(
    onBackClicked: () -> Unit,
    user: UserWithRoleResponse,
    onChangeRoleClicked: (UserUpdateRoleRequest) -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedRole by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .padding(4.dp)
            .wrapContentSize()
            .clickable {
                user.userId.let {

                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        user.let {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                user.email?.let { userEmail ->
                    Text(
                        text = userEmail,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 8.sp,
                        ),
                    )
                }
                user.fullName?.let { userName ->
                    Text(
                        text = userName,
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
                }
                user.role?.let { userRole ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.user_role),
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = TextStyle(
                                fontSize = 10.sp,
                            ),
                        )
                        Text(
                            text = userRole,
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = TextStyle(
                                fontSize = 10.sp,
                            ),
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
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
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                UserRole.entries.forEach { userRole ->
                    DropdownMenuItem(
                        text = { Text(text = userRole.displayName) },
                        onClick = {
                            expanded = false
                            selectedRole = userRole.name
                            val userRoleRequest =
                                UserUpdateRoleRequest(user.userId, selectedRole)
                            onChangeRoleClicked(userRoleRequest)
                        }
                    )
                }
            }

        }
    }
}