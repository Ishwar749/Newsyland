package com.example.newsyland.presentation.news_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsyland.presentation.component.NewsArticleCard

@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel = hiltViewModel()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(viewModel.articles) { article ->
            NewsArticleCard(article = article, onCardClicked = {})
        }
    }
}