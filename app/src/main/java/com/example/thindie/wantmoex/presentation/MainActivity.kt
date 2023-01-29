package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.thindie.wantmoex.presentation.composables.coinsFeature.CoinStateFun
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CoinViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            WANTMOEXTheme {
                CoinStateFun(viewModel)
            }
        }
    }
}