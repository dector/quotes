package io.github.dector.quotes.android.presentation.view

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

    private lateinit var loadingTextView: TextView
    private lateinit var loadingImageView: ImageView

    private lateinit var dataContainerView: View
    private lateinit var noDataContainerView: View
    private lateinit var loadingContainterView: View

    override fun init() {
        rootView = content.findViewById(R.id.quotes_root)
        touchView = content.findViewById(R.id.quotes_touch)
        quoteView = content.findViewById(R.id.quotes_quote) as TextView
        authorView = content.findViewById(R.id.quotes_author) as TextView

        loadingTextView = content.findViewById(R.id.quotes_loading_text) as TextView
        loadingImageView = content.findViewById(R.id.quotes_loading_image) as ImageView

        dataContainerView = content.findViewById(R.id.quotes_data_container)
        noDataContainerView = content.findViewById(R.id.quotes_no_data_container)
        loadingContainterView = content.findViewById(R.id.quotes_loading_container)

        touchView.setOnClickListener { listener?.nextQuote() }
    }

    override fun showDataState() {
        dataContainerView.visibility = View.VISIBLE
        noDataContainerView.visibility = View.GONE
        loadingContainterView.visibility = View.GONE
        loadingImageView.clearAnimation()
    }

    override fun showNoDataState() {
        dataContainerView.visibility = View.GONE
        noDataContainerView.visibility = View.VISIBLE
        loadingContainterView.visibility = View.GONE
        loadingImageView.clearAnimation()
    }

    override fun showLoadingState() {
        dataContainerView.visibility = View.GONE
        noDataContainerView.visibility = View.GONE
        loadingContainterView.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(loadingImageView, View.ROTATION, -90F, -270F).let {
            it.repeatMode = ObjectAnimator.RESTART
            it.repeatCount = ObjectAnimator.INFINITE
            it.duration = 700
            it.interpolator = DecelerateInterpolator()
            it.start()
        }
    }

    override fun showDisplayingError(message: String) {
        Toast.makeText(content.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showQuote(quote: String) {
        quoteView.text = quote
    }

    override fun showAuthor(author: String) {
        authorView.text = author
    }

    override fun textColor(color: Color) {
        val colorValue = color.solidValue()

        quoteView.setTextColor(colorValue)
        authorView.setTextColor(colorValue)
        loadingTextView.setTextColor(colorValue)
        loadingImageView.setColorFilter(colorValue)
    }

    override fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solidValue())
    }
}