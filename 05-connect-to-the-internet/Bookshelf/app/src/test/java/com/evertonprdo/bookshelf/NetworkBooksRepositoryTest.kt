package com.evertonprdo.bookshelf

import com.evertonprdo.bookshelf.data.NetworkBooksRepository
import com.evertonprdo.bookshelf.fake.FakeBooksApiService
import com.evertonprdo.bookshelf.fake.FakeDataSource
import com.evertonprdo.bookshelf.network.BooksMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_fetchBooks() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBooksApiService()
        )

        Assert.assertEquals(
            BooksMapper.toDomain(FakeDataSource.bookListApiResponse),
            repository.fetchBooks("jazz")
        )
    }
}