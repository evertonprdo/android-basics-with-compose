package com.evertonprdo.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.bookshelf.R
import com.evertonprdo.bookshelf.ui.screens.HomeScreen
import com.evertonprdo.bookshelf.ui.viewmodel.BookshelfViewModel

@Composable
fun BookshelfApp(
    bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
) {
    Scaffold(
        topBar = {
            AmphibiansTopAppBar(
                query = bookshelfViewModel.query,
                isQueryEmpty = bookshelfViewModel.isQueryEmpty,
                onSubmit = { bookshelfViewModel.fetchBooks() },
                onQueryChange = { bookshelfViewModel.updateQuery(it) }
            )
        },
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
fun AmphibiansTopAppBar(
    query: String,
    isQueryEmpty: Boolean,
    onSubmit: () -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleSmall
                )

                OutlinedTextField(
                    value = query,
                    onValueChange = { onQueryChange(it) },
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    textStyle = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        disabledContainerColor = colorScheme.surface,
                    ),
                    label = {
                        if (isQueryEmpty)
                            Text("Invalid query")
                        else
                            Text("Search")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onSubmit() }
                    ),
                    isError = isQueryEmpty,
                    trailingIcon = {
                        IconButton(
                            onClick = { onSubmit() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    },
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}