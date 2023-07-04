package com.example.newsyland.data.repository

import com.example.newsyland.data.remote.NewsApi
import com.example.newsyland.domain.model.Article
import com.example.newsyland.domain.repository.NewsRepository
import com.example.newsyland.util.Resource

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getNews(category = category)
            Resource.Success(data = response.articles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}