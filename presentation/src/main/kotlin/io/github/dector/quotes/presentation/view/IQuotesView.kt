package io.github.dector.quotes.presentation.view

import io.github.dector.quotes.presentation.IInitializable

interface IQuotesView : IInitializable {

    fun showDataState()
    fun showNoDataState()

    fun showQuote(quote: String)
    fun showAuthor(author: String)
    fun textColor(color: Color)
    fun backgroundColor(color: Color)
}