package io.github.dector.quotes.repositories

import kotlin.random.Random

class SimpleRandomizer<T, D>(
    initialValue: T,
    private val randomizeWith: (Random, D) -> T,
    private val random: Random = Random.Default
) {

    private var prevValue: T = initialValue

    fun next(argument: D): T {
        val value = run {
            var value = prevValue
            while (value == prevValue) {
                value = randomizeWith(random, argument)
            }
            value
        }

        prevValue = value
        return value
    }
}

val IndexRandomizer = SimpleRandomizer<Int, Int>(
    initialValue = -1,
    randomizeWith = { rnd, size -> rnd.nextInt(size) }
)
