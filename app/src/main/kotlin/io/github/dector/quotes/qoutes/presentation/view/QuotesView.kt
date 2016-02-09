package io.github.dector.quotes.qoutes.presentation.view

import android.view.View
import android.widget.TextView
import io.github.dector.quotes.qoutes.presentation.Color
import io.github.dector.quotes.R

class QuotesView(val layout: View) : IQuotesView {

    lateinit var actionListener: IQuotesActionListener

    private lateinit var rootView: View
    private lateinit var touchView: View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    fun setup() {
        rootView = layout.findViewById(R.id.main_root)
        touchView = layout.findViewById(R.id.main_touch)
        quoteView = layout.findViewById(R.id.main_quote) as TextView
        authorView = layout.findViewById(R.id.main_author) as TextView

        touchView.setOnClickListener { actionListener.displayQuote() }
    }

    override fun showQuote(quote: String) {
        quoteView.text = quote
    }

    override fun showAuthor(author: String) {
        authorView.text = author
    }

    override fun textColor(color: Color) {
        quoteView.setTextColor(color.solidValue())
        authorView.setTextColor(color.solidValue())
    }

    override fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solidValue())
    }
}