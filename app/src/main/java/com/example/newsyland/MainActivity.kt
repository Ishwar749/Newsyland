package com.example.newsyland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsyland.presentation.news_screen.NewsScreen
import com.example.newsyland.presentation.news_screen.NewsScreenViewModel
import com.example.newsyland.presentation.theme.NewsylandTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsylandTheme {
                NewsScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}
