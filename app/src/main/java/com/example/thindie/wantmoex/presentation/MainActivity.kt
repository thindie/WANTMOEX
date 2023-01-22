package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import com.example.thindie.wantmoex.presentation.theme.composables.LoadingState
import com.example.thindie.wantmoex.presentation.theme.composables.ShareItemExpanded
import com.example.thindie.wantmoex.presentation.theme.composables.WantMoexList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SharesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is SharesViewModel.SharesUIState.Loading -> {
                            setContent {
                                WANTMOEXTheme {
                                    LoadingState()
                                }
                            }
                        }
                        is SharesViewModel.SharesUIState.SuccessAllShare -> {
                            setContent {
                                WANTMOEXTheme {
                                    WantMoexList(
                                        list = it.shares,
                                        onClick = { share -> viewModel.loadShare(share) })
                                }
                            }
                        }
                        is SharesViewModel.SharesUIState.SuccessSingle -> {
                            setContent {
                                WANTMOEXTheme {
                                    ShareItemExpanded(list = it.shares) {
                                        viewModel.loadAllData()
                                    }
                                }
                            }
                        }
                        else -> {// TODO заглушка тут*/}
                        }
                    }
                }
            }
        }
    }
}



