package com.evertonprdo.bookshelf.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evertonprdo.bookshelf.BookshelfApplication
import com.evertonprdo.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookshelfViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    private val query = "software"

    init {
        fetchBooks()
    }

    fun fetchBooks() {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as BookshelfApplication)

                val booksRepository =
                    application.container.booksRepository

                BookshelfViewModel(booksRepository = booksRepository)
            }
        }
    }
}