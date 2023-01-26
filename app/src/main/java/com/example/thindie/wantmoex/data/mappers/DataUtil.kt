package com.example.thindie.wantmoex.data.mappers

import android.os.Build
import android.os.SystemClock
import java.util.concurrent.TimeUnit


private const val MULTIPLIER = 1000L


fun getHowLongAgo(coinUpdatedTime: Long): String {
    val currentTime = try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            SystemClock.currentNetworkTimeClock().millis()
        } else {
            System.currentTimeMillis()
        }
    } catch (e: Exception) {
        System.currentTimeMillis()
    }

    val timeDifference = currentTime - coinUpdatedTime.times(MULTIPLIER)
    return String.format(
        "%02d min, %02d sec",
        TimeUnit.MILLISECONDS.toMinutes(timeDifference),
        TimeUnit.MILLISECONDS.toSeconds(timeDifference) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDifference))
    )
}





