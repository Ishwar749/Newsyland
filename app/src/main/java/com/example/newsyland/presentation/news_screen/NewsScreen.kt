package com.example.newsyland.presentation.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.newsyland.domain.model.Article
import com.example.newsyland.presentation.component.CategoryTabRow
import com.example.newsyland.presentation.component.NewsArticleCard
import com.example.newsyland.presentation.component.NewsScreenTopBar
import com.example.newsyland.presentation.component.RetryContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent: (NewsScreenEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[page]))
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NewsScreenTopBar(scrollBehavior = scrollBehavior)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categories = categories,
                onTabSelected = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
            HorizontalPager(
                pageCount = categories.size,
                state = pagerState
            ) {
                NewsArticleList(
                    state = state,
                    onCardClicked = {},
                    onRetry = {
                        onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                    }
                )
            }
        }
    }
}

@Composable
fun NewsArticleList(
    modifier: Modifier = Modifier,
    state: NewsScreenState,
    onCardClicked: (Article) -> Unit,
    onRetry: () -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.articles) { article ->
            NewsArticleCard(
                article = article,
                onCardClicked = onCardClicked
            )
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            RetryContent(
                error = state.error,
                onRetry = onRetry
            )
        }
    }
}