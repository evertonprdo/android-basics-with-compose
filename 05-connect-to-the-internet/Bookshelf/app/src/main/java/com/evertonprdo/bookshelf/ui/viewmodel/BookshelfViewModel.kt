package com.evertonprdo.bookshelf.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evertonprdo.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookshelfViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        fetchBooks("software")
    }

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            bookshelfUiState = BookshelfUiState.Loading

            bookshelfUiState = try {
                BookshelfUiState.Success(booksRepository.fetchBooks(query = query))

            } catch (e: IOException) {
                BookshelfUiState.Error

            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }
}