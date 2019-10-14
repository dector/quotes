package io.github.dector.quotes.domain

data class Color(val value: Int) {
    companion object
}

val Color.solid: Int get() = 0xff000000.toInt() or value

data class ColorCouple(val foreground: Color, val background: Color)

data class Palette(val colors: List<ColorCouple>)
