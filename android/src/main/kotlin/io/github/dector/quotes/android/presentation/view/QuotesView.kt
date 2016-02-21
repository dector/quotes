package io.github.dector.quotes.android.presentation.view

import android.view.View
import android.widget.TextView
import io.github.dector.quotes.R
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView

class QuotesView(val content: View) : IQuotesView {

    var listener: IQuotesActionListener? = null

    private lateinit var rootView: View
    private lateinit var touchView: View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    private lateinit var dataContainerView: View
    private lateinit var noDataContainerView: View

    override fun init() {
        rootView = content.findViewById(R.id.quotes_root)
        touchView = content.findViewById(R.id.quotes_touch)
        quoteView = content.findViewById(R.id.quotes_quote) as TextView
        authorView = content.findViewById(R.id.quotes_author) as TextView

        dataContainerView = content.findViewById(R.id.quotes_data_container)
        noDataContainerView = content.findViewById(R.id.quotes_no_data_container)

        touchView.setOnClickListener { listener?.displayQuote() }
    }

    override fun showDataState() {
        dataContainerView.visibility = View.VISIBLE
        noDataContainerView.visibility = View.GONE
    }

    override fun showNoDataState() {
        dataContainerView.visibility = View.GONE
        noDataContainerView.visibility = View.VISIBLE
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