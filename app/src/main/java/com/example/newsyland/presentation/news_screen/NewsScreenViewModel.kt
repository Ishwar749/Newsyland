package com.example.newsyland.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsyland.domain.model.Article
import com.example.newsyland.domain.repository.NewsRepository
import com.example.newsyland.util.Resource
import kotlinx.coroutines.launch

class NewsScreenViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var articles by mutableStateOf<List<Article>>(emptyList())

    private fun getNewsArticles(category: String){
        viewModelScope.launch {
            val result = newsRepository.getTopHeadlines(category = category)

            when(result){
                is Resource.Success -> {
                    articles = result.data ?: emptyList()
                }
                is Resource.Error -> TODO()
            }
        }
    }
}