package com.evertonprdo.bookshelf.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BookListResponse(val items: List<BookItem> = emptyList())

@Serializable
data class BookItem(
    val id: String = "",
    val volumeInfo: VolumeInfo = VolumeInfo(),
    val etag: String = ""
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks = ImageLinks(),
    val title: String = "",
)

@Serializable
data class ImageLinks(
    val thumbnail: String = ""
)
