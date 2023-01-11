package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import com.example.thindie.wantmoex.presentation.theme.composables.WantMoex
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     private val viewModel: SharesViewModel by lazy {
         ViewModelProvider(this)[SharesViewModel::class.java]
     }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        viewModel.loadAllData()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val onClick = { share: Share -> viewModel.loadShare(share = share) }
                setContent {
                    WANTMOEXTheme {
                        WantMoex(viewModel, onClick)
                    }
                }
            }
        }

    }
}



