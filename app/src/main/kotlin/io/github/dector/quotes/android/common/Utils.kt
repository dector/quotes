package io.github.dector.quotes.android.common

import java.util.*
import java.util.concurrent.TimeUnit

private object Utils {

    val rnd: Random by lazy { Random() }
}

fun <T> Array<T>.random(): T? {
    return when {
        size > 0 -> get(Utils.rnd.nextInt(size))
        else -> null
    }
}

fun Date.add(time: Long, unit: TimeUnit) = Date(this.time + unit.toMillis(time))