package io.github.dector.quotes.common

import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    val rnd: Random by lazy { Random() }
}

fun <T> Array<T>.random(): T? = this[Utils.rnd.nextInt(size)]

fun <T> List<T>.random(): T? = this[Utils.rnd.nextInt(size)]

fun Int.randomUntil() =  if (this > 0) Utils.rnd.nextInt(this) else 0

fun Date.add(time: Long, unit: TimeUnit) = Date(this.time + unit.toMillis(time))