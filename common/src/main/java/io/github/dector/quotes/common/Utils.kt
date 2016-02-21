package io.github.dector.quotes.common

import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    val rnd: Random by lazy { Random() }
}

fun <T> Array<T>.random(): T? {
    return when {
        size > 0 -> get(Utils.rnd.nextInt(size))
        else -> null
    }
}

fun Int.randomUntil() =  if (this > 0) Utils.rnd.nextInt() else 0

fun Date.add(time: Long, unit: TimeUnit) = Date(this.time + unit.toMillis(time))