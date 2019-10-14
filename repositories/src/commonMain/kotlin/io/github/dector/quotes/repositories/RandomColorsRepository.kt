package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.ColorCouple

interface RandomColorsRepository {

    fun next(): ColorCouple
}
