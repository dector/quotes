package io.github.dector.quotes

import kotlin.random.Random

fun <T> Random.peek(data: List<T>): T {
    val index = nextInt(data.size)
    return data[index]
}
