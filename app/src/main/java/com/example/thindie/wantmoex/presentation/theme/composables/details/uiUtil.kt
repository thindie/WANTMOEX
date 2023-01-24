package com.example.thindie.wantmoex.presentation.theme.composables.details

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

private const val DELAY = 700L
private const val TO_SECONDS = 1000L
private const val TO_MINUTES = 1000L
private const val LOLONIMATION = "   "
private const val LOLOREPLACE = " "
private const val PUNTO = " "
private const val ALTCOIN = 10
private const val HOT = 5
private const val ILON_TWEET_IT = 5

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

val whenUpdated: (Long) -> String = { priceWasUpdated ->
    if (((System.currentTimeMillis() / TO_SECONDS - priceWasUpdated) / TO_MINUTES) > ALTCOIN) "> 10 min"
    else if (((System.currentTimeMillis() / TO_SECONDS - priceWasUpdated) / TO_MINUTES) > HOT) "> 5 min"
    else if (((System.currentTimeMillis() / TO_SECONDS - priceWasUpdated) / TO_MINUTES) > ILON_TWEET_IT) "> 1 min"
    else "fresh"
}

