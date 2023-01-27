package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thindie.wantmoex.presentation.composables.CoinErrorScreen
import com.example.thindie.wantmoex.presentation.composables.CoinHomeScreen
import com.example.thindie.wantmoex.presentation.composables.CoinLoadScreen
import com.example.thindie.wantmoex.presentation.composables.StartScreen
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   // private val viewModel: CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {


                    setContent {
                        WANTMOEXTheme{
                            StartScreen()
                        }
                    }
                }

            }
        }
    }



