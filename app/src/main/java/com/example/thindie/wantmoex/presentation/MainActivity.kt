package com.example.thindie.wantmoex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thindie.wantmoex.di.DaggerWMComponent
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: SharesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWMComponent.create().inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            WANTMOEXTheme {

            }
        }
    }
}

