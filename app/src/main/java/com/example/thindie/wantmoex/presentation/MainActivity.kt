package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SharesViewModel by lazy {
        ViewModelProvider(this)[SharesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            WANTMOEXTheme {

            }
        }
    }
}

