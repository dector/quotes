package io.github.dector.quotes.qoutes.presentation.view

data class ColorPair(val text: Color, val background: Color)

enum class Color(val value: Int) {
    WHITE(0xffffff),
    BLACK(0x000000),

    // 500 colors
    RED(0xf44336),
    PINK(0xe91e63),
    PURPLE(0x9c27b0),
    DEEP_PURPLE(0x673ab7),
    INDIGO(0x3f51b5),
    BLUE(0x2196f3),
    LIGHT_BLUE(0x03a9f4),
    CYAN(0x00bcd4),
    TEAL(0x009688),
    GREEN(0x4caf50),
    LIGHT_GREEN(0x8bc34a),
    LIME(0xcddc39),
    YELLOW(0xffeb3b),
    AMBER(0xffc107),
    ORANGE(0xff9800),
    DEEP_ORANGE(0xff5722),
    BROWN(0x795548),
    GREY(0x9e9e9e),
    BLUE_GREY(0x607d8b);

    fun solidValue() = 0xff000000.toInt() or value
}