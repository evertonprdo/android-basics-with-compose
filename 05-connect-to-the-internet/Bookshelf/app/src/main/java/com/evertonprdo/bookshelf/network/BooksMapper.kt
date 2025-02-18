package com.evertonprdo.bookshelf.network

import com.evertonprdo.bookshelf.model.Book
import com.evertonprdo.bookshelf.network.model.BookListResponse

object BooksMapper {
    fun toDomain(listResponse: BookListResponse): List<Book> {
        return listResponse.items.map { bookItem ->
            Book(
                id = bookItem.id,
                imgSrc = bookItem.volumeInfo.imageLinks.thumbnail
            )
        }
    }
}
