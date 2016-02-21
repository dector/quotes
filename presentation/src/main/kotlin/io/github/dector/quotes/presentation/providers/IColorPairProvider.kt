package io.github.dector.quotes.presentation.providers

import io.github.dector.quotes.presentation.view.ColorPair

interface IColorPairProvider {

    fun getRandomColorPair(): ColorPair
}