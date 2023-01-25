package com.example.thindie.wantmoex.data.mappers

import android.os.Build
import android.os.SystemClock
import java.util.concurrent.TimeUnit

private const val PATTERN = "hh-mm:ss"
private const val MULTIPLIER = 1000L
private const val MINUTES = " min"


fun getHowLongAgo(time: Long): String {
    val now = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            SystemClock.currentNetworkTimeClock().millis()
        } else {
            System.currentTimeMillis()
        }
    } catch (e: Exception) {
        System.currentTimeMillis()
    }

    val difference = now - time.times(MULTIPLIER)
    return String.format(
        "%02d min, %02d sec",
        TimeUnit.MILLISECONDS.toMinutes(difference),
        TimeUnit.MILLISECONDS.toSeconds(difference) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))
    )
}





