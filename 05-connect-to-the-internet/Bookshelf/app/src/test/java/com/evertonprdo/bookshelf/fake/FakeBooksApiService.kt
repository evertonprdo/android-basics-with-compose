package com.evertonprdo.bookshelf.fake

import com.evertonprdo.bookshelf.network.BooksApiService
import com.evertonprdo.bookshelf.network.model.BookListResponse

class FakeBooksApiService : BooksApiService {

    override suspend fun fetchBooks(
        query: String,
        maxResults: String
    ): BookListResponse {
        return FakeDataSource.bookListApiResponse
    }
}