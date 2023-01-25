package com.example.thindie.wantmoex.presentation.theme.composables.details

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

private const val LOLONIMATION = "  "
private const val LOLOREPLACE = " "
private const val PUNTO = "."
private const val DELAY = 300L


suspend fun playWithTitle(title: String, state: MutableState<String>) {
    val innerTitle = title.plus(LOLONIMATION)
    var doValue: String
    for (i in 0..1) {
        doValue = innerTitle
        for (i1 in 0..1) {
            doValue = doValue.replaceFirst(LOLOREPLACE, PUNTO)
            delay(DELAY)
            state.value = doValue
        }
    }
    state.value = innerTitle
}

