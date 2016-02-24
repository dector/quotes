package io.github.dector.knight.common

import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    val rnd: Random by lazy { Random() }
}

fun <T> Array<T>.random(): T? = if (this.size > 0) this[Utils.rnd.nextInt(size)] else null

fun <T> List<T>.random(): T? = if (this.size > 0) this[Utils.rnd.nextInt(size)] else null

fun Int.randomUntil() =  if (this > 0) Utils.rnd.nextInt(this) else 0