package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                Box(modifier = Modifier.fillMaxSize()) {


                    Column {

                        Button(onClick = { viewModel.onChoseCoin("BTC") }) {
                            state.value.coin?.fromSymbol?.let { Text(it) }
                        }

                        Button(onClick = { viewModel.onShowList(null) }) {
                            Text("")
                        }
                    }
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        if(state.value.isLoading){
                            CircularProgressIndicator(modifier = Modifier.size(72.dp))}
                    }
                }

             }
        }
    }
}