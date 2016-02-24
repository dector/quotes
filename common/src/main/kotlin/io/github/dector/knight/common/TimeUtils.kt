package io.github.dector.knight.common

import java.util.*
import java.util.concurrent.TimeUnit

// ----- Date processing -----

fun Date.add(time: Long, unit: TimeUnit) = Date(this.time + unit.toMillis(time))

// ----- Time processing -----

fun Int.secondsAsMillis(): Long = this * 1000L

fun Int.minutesAsMillis(): Long = this * 60.secondsAsMillis()