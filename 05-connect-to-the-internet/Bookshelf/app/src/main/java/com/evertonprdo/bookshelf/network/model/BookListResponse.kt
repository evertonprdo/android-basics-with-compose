package com.evertonprdo.bookshelf.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BookListResponse(val items: List<BookItem>)

@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks,
    val title: String,
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)
