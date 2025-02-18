package com.evertonprdo.bookshelf.network

import com.evertonprdo.bookshelf.model.Book
import com.evertonprdo.bookshelf.network.model.BookListResponse

object BooksMapper {
    fun toDomain(listResponse: BookListResponse): List<Book> {
        return listResponse.items.map { bookItem ->
            Book(
                id = bookItem.id,
                etag = bookItem.etag,
                title = bookItem.volumeInfo.title,
                imgSrc = httpToHttps(bookItem.volumeInfo.imageLinks.thumbnail),
            )
        }
    }

    private fun httpToHttps(uri: String): String =
        uri.replace("http", "https")

}
