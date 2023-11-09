package com.example.core.designelements.components.screenelements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBars(isInDarkTheme: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isInDarkTheme
    val color = Color.Transparent

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}
