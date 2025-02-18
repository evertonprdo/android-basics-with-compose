package com.evertonprdo.bookshelf.network

import com.evertonprdo.bookshelf.network.model.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("books/v1/volumes")
    suspend fun fetchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: String = "20"
    ): BookListResponse
}