package com.evertonprdo.bookshelf.data

import com.evertonprdo.bookshelf.model.Book
import com.evertonprdo.bookshelf.network.BooksApiService
import com.evertonprdo.bookshelf.network.BooksMapper

interface BooksRepository {
    suspend fun fetchBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun fetchBooks(query: String): List<Book> =
        BooksMapper.toDomain(booksApiService.fetchBooks(query))
}