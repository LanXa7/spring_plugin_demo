package com.example.http.request

data class ArticleRequest(
    val title: String,
    val content: String,
    val tags: List<String>
)