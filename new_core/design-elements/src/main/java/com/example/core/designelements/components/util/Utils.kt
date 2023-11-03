package com.example.core.designelements.components.util

import android.os.Build
import android.os.SystemClock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.core.designelements.theme.CryptoViewTheme
import java.util.concurrent.TimeUnit

private const val MILLIS_MULTIPLIER = 1000L
private const val TIME_PATTERN = "%02dm :%02ds"


@Composable
fun String.HeadLineText(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.HeadLineTextNews(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.surfaceTint.copy(0.5f),
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier
        )
    }

@Composable
fun String.NewsText(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier

        )
    }

@Composable
fun String.BodyText(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.MiniText(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.MediumText(modifier: Modifier = Modifier) =
    CryptoViewTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

/**
 * *Преобразует время в миллисекундах в строку в формате минуты:секунды
 * @return строку, содержащую время в формате минуты:секунды
 */

fun Long.timeInMillisToString(): String {
    // Определяем текущее время, используя сетевые настройки, если доступно
    val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        SystemClock.currentNetworkTimeClock().millis()
    } else {
        System.currentTimeMillis()
    }
    // Вычисляем разницу между текущим временем и переданным временем
    val timeDifference = currentTime - this.times(MILLIS_MULTIPLIER)
    // Возвращаем строку в формате минуты:секунды
    return String.format(
        TIME_PATTERN,
        TimeUnit.MILLISECONDS.toMinutes(timeDifference),
        TimeUnit.MILLISECONDS.toSeconds(timeDifference) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDifference))
    )
}
