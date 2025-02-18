package com.evertonprdo.bookshelf.fake

import com.evertonprdo.bookshelf.data.BooksRepository
import com.evertonprdo.bookshelf.model.Book
import com.evertonprdo.bookshelf.network.BooksMapper

class FakeNetworkBooksRepository : BooksRepository {

    override suspend fun fetchBooks(query: String): List<Book> {
        return BooksMapper.toDomain(FakeDataSource.bookListApiResponse)
    }
}