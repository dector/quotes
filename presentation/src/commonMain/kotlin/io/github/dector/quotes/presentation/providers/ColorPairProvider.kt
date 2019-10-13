package io.github.dector.quotes.presentation.providers

import io.github.dector.knight.common.random
import io.github.dector.quotes.presentation.view.Color.*
import io.github.dector.quotes.presentation.view.ColorPair

class ColorPairProvider : IColorPairProvider {

    private val DEFAULT_COLOR_PAIR = ColorPair(WHITE, BLACK)

    val colors = arrayOf(
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

    override fun getRandomColorPair(): ColorPair {
        var pair = randomPair()

        while (pair == previousPair && previousPair != DEFAULT_COLOR_PAIR) {
            pair = randomPair()
        }

        previousPair = pair
        return pair
    }

    private fun randomPair() = colors.random() ?: DEFAULT_COLOR_PAIR
}
