package com.evertonprdo.bookshelf.ui.viewmodel

import com.evertonprdo.bookshelf.model.Book

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}
