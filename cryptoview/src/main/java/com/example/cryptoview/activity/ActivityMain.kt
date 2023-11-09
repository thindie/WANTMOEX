package com.example.cryptoview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.example.core.designelements.components.screenelements.TransparentSystemBars
import com.example.core.designelements.theme.CryptoViewTheme
import com.example.core.presentation.uicoin.coinscreen.CoinsScreen
import com.example.cryptoview.application.di.AppComponent
import javax.inject.Inject

class ActivityMain : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initDaggerComponent()
        setContent {
            CryptoViewTheme(useDarkTheme = true) {
                TransparentSystemBars(isInDarkTheme = true)
                CoinsScreen(factory = factory)
            }
        }
    }

    private fun initDaggerComponent() {
        AppComponent.init(applicationContext).inject(this)
    }
}
