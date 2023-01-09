package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.presentation.theme.Shapes
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: SharesViewModel by lazy {
        ViewModelProvider(this)[SharesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val onClick = { share: Share -> viewModel.loadShare(share = share) }
                setContent {
                    WANTMOEXTheme {
                        MyScreen(viewModel, onClick)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyScreen(viewModel: SharesViewModel, onClick: (Share) -> Unit) {
    Box {
        val myScreenState = viewModel.uiState.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .imeNestedScroll()
        ) // scroll IME at the bottom
        {
            when (myScreenState.value) {
                is SharesViewModel.SharesUIState.Success -> {
                    items(
                        (myScreenState.value
                                as SharesViewModel.SharesUIState.Success).shares
                    ) { item: Share ->
                        ShareItem(share = item) { onClick(item) }
                    }
                }
                is SharesViewModel.SharesUIState.Loading -> {}
                else -> {}
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsPadding() // padding for navigation bar
                .imePadding(), // padding for when IME appears
            onClick = { }
        ) {
            //  Icon( )
        }
    }
}

@Composable
fun ShareItem(share: Share, onClick: () -> Unit) {
    Card(shape = Shapes.extraLarge, modifier = Modifier.clickable { onClick() }) {
        Row() {
            Text(text = share.id)
            Text(text = share.prevPrice)
            Text(text = share.shortName)
        }
    }
}

