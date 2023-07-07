package com.example.newsyland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsyland.presentation.news_screen.NewsScreen
import com.example.newsyland.presentation.theme.NewsylandTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsylandTheme {
                NewsScreen()
            }
        }
    }
}
