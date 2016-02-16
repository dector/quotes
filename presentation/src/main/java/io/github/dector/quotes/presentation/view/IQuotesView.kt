package io.github.dector.quotes.presentation.view

import io.github.dector.quotes.qoutes.presentation.view.Color

interface IQuotesView {

    fun showDataState()
    fun showNoDataState()

    fun showQuote(quote: String)
    fun showAuthor(author: String)
    fun textColor(color: Color)
    fun backgroundColor(color: Color)
}
