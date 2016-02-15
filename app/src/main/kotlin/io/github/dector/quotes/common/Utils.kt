package io.github.dector.quotes.common

import java.util.*

private object Utils {

    val rnd: Random by lazy { Random() }
}

fun <T> Array<T>.random(): T? {
    return when {
        size > 0 -> get(Utils.rnd.nextInt(size))
        else -> null
    }
}