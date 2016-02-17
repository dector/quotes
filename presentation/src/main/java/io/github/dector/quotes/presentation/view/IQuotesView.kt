package io.github.dector.quotes.presentation.view

interface IQuotesView {

    fun showDataState()
    fun showNoDataState()

    fun showQuote(quote: String)
    fun showAuthor(author: String)
    fun textColor(color: Color)
    fun backgroundColor(color: Color)
}
