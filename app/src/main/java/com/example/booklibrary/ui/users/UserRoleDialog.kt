package com.example.booklibrary.ui.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.ItemContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.booklibrary.R
import com.example.booklibrary.data.SampleData
import com.example.booklibrary.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRoleDialog(user: User, onSubmit: (String) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var userRole by remember {
        mutableStateOf(user.role)
    }
    Dialog(onDismissRequest = {  }) {
        ElevatedCard(
            onClick = { },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp)
            ) {
                userRole?.let {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        value = it,
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                        label = {
                            Text(
                                text = stringResource(id = R.string.choose_role)
                            )
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = TextFieldDefaults.colors(focusedTextColor = Color.Black)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.exposedDropdownSize()
                ) {
                    SampleData.role.forEach { role ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = role,
                                )
                            },
                            onClick = {
                                expanded = false
                                userRole = role
                            },
                            contentPadding = ItemContentPadding,
                            colors = MenuDefaults.itemColors(textColor = Color.Black)
                        )
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.submit),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp,
                        end = 24.dp
                    )
                    .clickable {
                        userRole?.let { onSubmit(it) }
                    }
            )
        }
    }
}