package io.github.dector.quotes.presentation.providers

import io.github.dector.quotes.common.random
import io.github.dector.quotes.presentation.view.Color.*
import io.github.dector.quotes.presentation.view.ColorPair

class ColorPairProvider : IColorPairProvider {

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


    override fun getRandomColorPair() = colors.random() ?: ColorPair(WHITE, BLACK)
}
