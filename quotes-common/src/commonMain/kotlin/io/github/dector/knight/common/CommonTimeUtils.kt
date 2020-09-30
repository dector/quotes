package io.github.dector.knight.common

// ----- Time processing -----

fun Int.secondsAsMillis(): Long = this * 1000L

fun Int.minutesAsMillis(): Long = this * 60.secondsAsMillis()

expect fun currentTimeMillis(): Long
