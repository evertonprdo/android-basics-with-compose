package com.evertonprdo.bookshelf

import com.evertonprdo.bookshelf.fake.FakeDataSource
import com.evertonprdo.bookshelf.fake.FakeNetworkBooksRepository
import com.evertonprdo.bookshelf.network.BooksMapper
import com.evertonprdo.bookshelf.rules.TestDispatcherRule
import com.evertonprdo.bookshelf.ui.viewmodel.BookshelfUiState
import com.evertonprdo.bookshelf.ui.viewmodel.BookshelfViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookshelfViewModel_fetchBooks_verifyBookshelfUiStateSuccess() =
        runTest {
            val bookshelfViewModel = BookshelfViewModel(
                booksRepository = FakeNetworkBooksRepository()
            )

            assertEquals(
                BookshelfUiState.Success(BooksMapper.toDomain(FakeDataSource.bookListApiResponse)),
                bookshelfViewModel.bookshelfUiState
            )
        }
}