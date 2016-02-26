package io.github.dector.quotes.presentation.view

import io.github.dector.quotes.presentation.IInitializable

interface IQuotesView : IInitializable {

    fun showDataState()
    fun showNoDataState()
    fun showLoadingState()
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showDisplayingError(message: String)

    fun disableUserInteraction()
    fun enableUserInteraction()

    fun showQuote(quote: String)
    fun showAuthor(author: String)
    fun textColor(color: Color)
    fun backgroundColor(color: Color)
}
