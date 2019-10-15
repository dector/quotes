package io.github.dector.quotes.colors

data class Color(val value: Int) {
    companion object
}

data class ColorCouple(val foreground: Color, val background: Color)
data class Palette(val colors: List<ColorCouple>)

inline val Color.Companion.Black get() = Color(0x000000)
inline val Color.Companion.White get() = Color(0xffffff)

val Color.r: Int get() = (0x00ff0000 and value) ushr 16
val Color.g: Int get() = (0x0000ff00 and value) ushr 8
val Color.b: Int get() = (0x000000ff and value)
val Color.rgb: Int get() = 0xff000000.toInt() or value

fun Color.counterpartColor(): Color {
    val luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b

    return if (luminance < 140) Color.White else Color.Black
}

object MaterialPalette {
    val colors500 = listOf(
        Color(0xF44336),
        Color(0xE91E63),
        Color(0x9C27B0),
        Color(0x673AB7),
        Color(0x3F51B5),
        Color(0x2196F3),
        Color(0x03A9F4),
        Color(0x00BCD4),
        Color(0x009688),
        Color(0x4CAF50),
        Color(0x8BC34A),
        Color(0xCDDC39),
        Color(0xFFEB3B),
        Color(0xFFC107),
        Color(0xFF9800),
        Color(0xFF5722),
        Color(0x795548),
        Color(0x9E9E9E),
        Color(0x607D8B)
    )
}

fun List<Color>.asPalette(): Palette = this
    .map { ColorCouple(it.counterpartColor(), it) }
    .let(::Palette)

