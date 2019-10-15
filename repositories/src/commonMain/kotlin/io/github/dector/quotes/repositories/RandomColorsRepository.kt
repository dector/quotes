package io.github.dector.quotes.repositories

import io.github.dector.quotes.colors.ColorCouple

interface RandomColorsRepository {

    fun next(): ColorCouple
}
