package com.evertonprdo.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.bookshelf.R
import com.evertonprdo.bookshelf.ui.screens.HomeScreen
import com.evertonprdo.bookshelf.ui.viewmodel.BookshelfViewModel

@Composable
fun BookshelfApp(
    bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
) {
    Scaffold(
        topBar = { AmphibiansTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreen(
            bookshelfUiState = bookshelfViewModel.bookshelfUiState,
            retryAction = bookshelfViewModel::fetchBooks,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) }
    )
}