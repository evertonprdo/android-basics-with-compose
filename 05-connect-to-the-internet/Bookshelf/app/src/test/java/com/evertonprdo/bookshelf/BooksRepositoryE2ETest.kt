package com.evertonprdo.bookshelf

import com.evertonprdo.bookshelf.data.DefaultAppContainer
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BooksRepositoryE2ETest {

    @Test
    fun booksRepository_fetchBooks() = runTest {
        val repository = DefaultAppContainer()

        val books = async {
            repository.booksRepository.getBooks("jazz")
        }.await()

        assertNotNull(books)
        assertTrue(books.isNotEmpty())

        books.forEach { book ->
            assertTrue(book.id.isNotBlank())
            assertTrue(book.imgSrc.isNotBlank())
        }
    }
}