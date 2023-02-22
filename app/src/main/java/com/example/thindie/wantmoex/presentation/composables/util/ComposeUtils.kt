package com.example.thindie.wantmoex.presentation.composables.util

import android.os.Build
import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme
import java.util.concurrent.TimeUnit


const val BTC = "BTC"
const val ETH = "ETH"
const val XRP = "XRP"
const val DOGE = "DOGE"
const val SHIBA = "SHIBA"
private const val MILLIS = 1000L

@Composable
fun String.HeadLine() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
@Composable
fun String.HeadLineNews() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.surfaceTint.copy(0.5f),
            softWrap = true,
            textAlign = TextAlign.Justify
        )
    }

@Composable
fun String.News() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
            softWrap = true,
            textAlign = TextAlign.Justify

        )
    }



@Composable
fun String.Body() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

@Composable
fun String.Mini() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

@Composable
fun String.Medium() =
    WANTMOEXTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }


fun Modifier.eightEndPadding() = this.padding(end = 8.dp)
fun Modifier.eightStartPadding() = this.padding(start = 8.dp)
fun Modifier.surfaceColor() = composed { this.background(MaterialTheme.colorScheme.surface) }
fun Modifier.onSurfaceColor() = composed { this.background(MaterialTheme.colorScheme.onSurface) }

fun Modifier.halfScreenColumns() = this.fillMaxWidth(0.5f)
fun Long.toTime(): String {
    val currentTime = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            SystemClock.currentNetworkTimeClock().millis()
        } else {
            System.currentTimeMillis()
        }
    } catch (e: Exception) {
        System.currentTimeMillis()
    }

    val timeDifference = currentTime - this.times(MILLIS)
    return String.format(
        "%02d:%02d sec",
        TimeUnit.MILLISECONDS.toMinutes(timeDifference),
        TimeUnit.MILLISECONDS.toSeconds(timeDifference) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDifference))
    )
}
