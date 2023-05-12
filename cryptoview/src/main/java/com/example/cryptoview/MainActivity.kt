package com.example.cryptoview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.core.designelements.theme.CryptoViewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            val expanded = checkWindowSizeIsExpanded()
            CryptoViewTheme(
                useDarkTheme = isDarkTheme
            ) {
                CryptoViewApp(
                    isLandscapeOrExtraWide = expanded,
                    isSystemDarkThemed = isDarkTheme,
                    executeIntent = { intent -> this.startActivity(intent) }
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    internal fun checkWindowSizeIsExpanded(): Boolean {
        val windowSize = calculateWindowSizeClass(activity = this)
        return when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> {
                true
            }
            else -> false
        }
    }
}
