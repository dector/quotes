package io.github.dector.quotes.repositories

import io.github.dector.quotes.colors.ColorCouple
import io.github.dector.quotes.colors.Palette

class RealRandomColorsRepository(
    private val palette: Palette,
    private val initialValue: ColorCouple,
    private val randomizer: SimpleRandomizer<ColorCouple, Palette> = PaletteRandomizer(initialValue)
) : RandomColorsRepository {

    init {
        require(palette.colors.isNotEmpty()) { "Palettes should be not empty" }
    }

    override fun next(): ColorCouple =
        randomizer.next(palette)
}

private fun PaletteRandomizer(initial: ColorCouple) =
    SimpleRandomizer<ColorCouple, Palette>(
        initialValue = initial,
        randomizeWith = { rnd, palette -> palette.colors.random(rnd) }
    )
