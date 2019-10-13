package io.github.dector.knight.common

import kotlin.random.Random

fun <T> Array<T>.random(): T? =
    if (isNotEmpty())
        get(Random.nextInt(size))
    else null

fun <T> List<T>.random(): T? =
    if (isNotEmpty())
        get(Random.nextInt(size))
    else null
