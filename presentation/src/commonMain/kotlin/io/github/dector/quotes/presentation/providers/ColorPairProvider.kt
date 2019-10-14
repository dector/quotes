package io.github.dector.quotes.presentation.providers

import io.github.dector.knight.common.random
import io.github.dector.quotes.presentation.view.LegacyColor.AMBER
import io.github.dector.quotes.presentation.view.LegacyColor.BLACK
import io.github.dector.quotes.presentation.view.LegacyColor.BLUE
import io.github.dector.quotes.presentation.view.LegacyColor.BLUE_GREY
import io.github.dector.quotes.presentation.view.LegacyColor.BROWN
import io.github.dector.quotes.presentation.view.LegacyColor.CYAN
import io.github.dector.quotes.presentation.view.LegacyColor.DEEP_ORANGE
import io.github.dector.quotes.presentation.view.LegacyColor.DEEP_PURPLE
import io.github.dector.quotes.presentation.view.LegacyColor.GREEN
import io.github.dector.quotes.presentation.view.LegacyColor.GREY
import io.github.dector.quotes.presentation.view.LegacyColor.INDIGO
import io.github.dector.quotes.presentation.view.LegacyColor.LIGHT_BLUE
import io.github.dector.quotes.presentation.view.LegacyColor.LIGHT_GREEN
import io.github.dector.quotes.presentation.view.LegacyColor.LIME
import io.github.dector.quotes.presentation.view.LegacyColor.ORANGE
import io.github.dector.quotes.presentation.view.LegacyColor.PINK
import io.github.dector.quotes.presentation.view.LegacyColor.PURPLE
import io.github.dector.quotes.presentation.view.LegacyColor.RED
import io.github.dector.quotes.presentation.view.LegacyColor.TEAL
import io.github.dector.quotes.presentation.view.LegacyColor.WHITE
import io.github.dector.quotes.presentation.view.LegacyColor.YELLOW
import io.github.dector.quotes.presentation.view.ColorPair

object ColorPairProvider {

    private val DEFAULT_COLOR_PAIR = ColorPair(WHITE, BLACK)

    val colors = listOf(
            ColorPair(WHITE, RED),
            ColorPair(WHITE, PINK),
            ColorPair(WHITE, PURPLE),
            ColorPair(WHITE, DEEP_PURPLE),
            ColorPair(WHITE, INDIGO),
            ColorPair(WHITE, BLUE),
            ColorPair(BLACK, LIGHT_BLUE),
            ColorPair(BLACK, CYAN),
            ColorPair(WHITE, TEAL),
            ColorPair(BLACK, GREEN),
            ColorPair(BLACK, LIGHT_GREEN),
            ColorPair(BLACK, LIME),
            ColorPair(BLACK, YELLOW),
            ColorPair(BLACK, AMBER),
            ColorPair(BLACK, ORANGE),
            ColorPair(WHITE, DEEP_ORANGE),
            ColorPair(WHITE, BROWN),
            ColorPair(BLACK, GREY),
            ColorPair(WHITE, BLUE_GREY)
    )

    private var previousPair: ColorPair? = null

    fun getRandomColorPair(): ColorPair {
        var pair = randomPair()

        while (pair == previousPair && previousPair != DEFAULT_COLOR_PAIR) {
            pair = randomPair()
        }

        previousPair = pair
        return pair
    }

    private fun randomPair() = colors.random() ?: DEFAULT_COLOR_PAIR
}
