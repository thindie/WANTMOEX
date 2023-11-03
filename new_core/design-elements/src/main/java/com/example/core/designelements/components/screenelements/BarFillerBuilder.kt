package com.example.core.designelements.components.screenelements

import androidx.compose.ui.graphics.vector.ImageVector

fun asBarFiller(imageVector: ImageVector, route: String): BarFiller {
    return object : BarFiller {
        override val icon: ImageVector = imageVector
        override val route: String = route
    }
}
