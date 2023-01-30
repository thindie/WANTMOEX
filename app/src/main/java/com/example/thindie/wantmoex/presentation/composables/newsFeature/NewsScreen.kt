package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Moving
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.NewsActivity
import com.example.thindie.wantmoex.presentation.composables.util.animateTextByDotsOnStateBased
import com.example.thindie.wantmoex.route.beginTransition
import kotlinx.coroutines.launch


private const val TITLE = "News "


@Composable
fun NewsScreen(
    newsList: List<News>

) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scaffoldScope = rememberCoroutineScope()

    val title = remember {
        mutableStateOf(TITLE)
    }
    val drawerMenuItems = listOf(
        DrawerMenuItem(
            Icons.Default.Home,
            "Home",
            MaterialTheme.typography.bodyLarge
        ) { },
        DrawerMenuItem(
            Icons.Default.Settings,
            "Set",
            MaterialTheme.typography.bodyLarge
        ) { },
        DrawerMenuItem(
            Icons.Default.Moving,
            "Others",
            MaterialTheme.typography.bodyLarge
        ) { },
    )

    LaunchedEffect(key1 = true) {
        animateTextByDotsOnStateBased(title.value, title)
    }


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { NewsDrawerHeader(); NewsDrawerBody(menuPoint = drawerMenuItems) },
        drawerBackgroundColor = MaterialTheme.colorScheme.surface,
        backgroundColor = MaterialTheme.colorScheme.surface,
        drawerElevation = 80.dp,
        bottomBar = {
            NewsBottomBar { beginTransition<NewsActivity, MainActivity>(context) }
        },
        topBar = { NewsTopAppBar(title = TITLE) { scaffoldScope.launch { scaffoldState.drawerState.open() } } },

        )
    { paddingValues ->
        title.value = TITLE
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(newsList) {
                NewsElement(news = it)
            }
        }
    }
}

