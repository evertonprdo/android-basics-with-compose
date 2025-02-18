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
                    volumeInfo = VolumeInfo(ImageLinks(thumbnail = "uri.1"))
                ),
                BookItem(
                    id = "book-02",
                    volumeInfo = VolumeInfo(ImageLinks(thumbnail = "uri.2"))
                ),
                BookItem(
                    id = "book-03",
                    volumeInfo = VolumeInfo(ImageLinks(thumbnail = "uri.3"))
                ),
            )
        )
}