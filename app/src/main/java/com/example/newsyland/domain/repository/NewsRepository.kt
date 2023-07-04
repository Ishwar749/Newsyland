package com.example.newsyland.domain.repository

import com.example.newsyland.domain.model.Article
import com.example.newsyland.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>
}