package com.evertonprdo.bookshelf.fake

import com.evertonprdo.bookshelf.network.model.BookItem
import com.evertonprdo.bookshelf.network.model.BookListResponse
import com.evertonprdo.bookshelf.network.model.ImageLinks
import com.evertonprdo.bookshelf.network.model.VolumeInfo

object FakeDataSource {

    val bookListApiResponse: BookListResponse =
        BookListResponse(
            items = listOf(
                BookItem(
                    id = "book-01",
                    etag = "etag-01",
                    volumeInfo = VolumeInfo(
                        title = "title-01",
                        imageLinks = ImageLinks(thumbnail = "uri.1"),
                    )
                ),
                BookItem(
                    id = "book-02",
                    etag = "etag-02",
                    volumeInfo = VolumeInfo(
                        title = "title-02",
                        imageLinks = ImageLinks(thumbnail = "uri.2"),
                    )
                ),
                BookItem(
                    id = "book-03",
                    etag = "etag-02",
                    volumeInfo = VolumeInfo(
                        title = "title-03",
                        imageLinks = ImageLinks(thumbnail = "uri.3"),
                    )
                ),
            )
        )
}