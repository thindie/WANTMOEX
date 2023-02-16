package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CoinViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        viewModel.onStart()
        setContent {
            WANTMOEXTheme {
                val state = viewModel.viewState.collectAsStateWithLifecycle()
                LaunchedEffect(true) {
                    while (true) {
                        delay(1100)
                        Log.d("SERVICE_TAG", "${state.value}")
                    }
                }

            }
        }
    }
}