package io.github.dector.quotes.qoutes.presentation.view

import io.github.dector.quotes.qoutes.presentation.Color

interface IQuotesView {

    fun showQuote(quote: String)
    fun showAuthor(author: String)
    fun textColor(color: Color)
    fun backgroundColor(color: Color)
}
