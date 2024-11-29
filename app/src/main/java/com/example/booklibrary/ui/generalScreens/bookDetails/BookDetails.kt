package com.example.booklibrary.ui.generalScreens.bookDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.R
import com.example.booklibrary.data.book.viewModels.BookCheckoutViewModel
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.showToast

@Composable
fun BookDetails(
    book: com.example.booklibrary.data.book.models.Book,
    onBackClicked: () -> Unit,
    onBorrowClick: () -> Unit,
    bookCheckoutViewModel: BookCheckoutViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val checkoutMessage = bookCheckoutViewModel.checkoutMessage.collectAsState().value
    LaunchedEffect(checkoutMessage) {
        when(checkoutMessage){
            is Resource.Error -> {
                context.showToast(message = checkoutMessage.message.toString())
            }
            is Resource.Success -> {

            }
            is Resource.Loading -> {

            }
        }
    }
    Scaffold(topBar = {
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
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding(), bottom = 80.dp)) {
            item {
                BookDetailsSection(book)
            }
            item {
                AboutBook(book)
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onBorrowClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .padding(bottom = 40.dp)
                            .height(60.dp),
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.borrow),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                }
            }
        }
    }
}
