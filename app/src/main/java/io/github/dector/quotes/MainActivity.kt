package io.github.dector.quotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var presenter: Presenter
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById(R.id.root) as ViewGroup

        presenter = Presenter(QuotesFactory())
        view = View(LayoutInflater.from(this).inflate(R.layout.view_main, root, false))

        root.removeAllViews()
        root.addView(view.layout)

        presenter.view = view
        view.actionListener = presenter

        presenter.init()
    }
}

interface IActionListener {

    fun displayQuote()
}

class Presenter(val quotesFactory: QuotesFactory) : IActionListener {

    lateinit var view: View

    fun init() {
        view.setup()

        displayQuote()
    }

    override fun displayQuote() {
        val quote = quotesFactory.randomQuote()
        view.showAuthor(quote.author)
        view.showQuote(quote.quote)
    }
}

class View(val layout: android.view.View) {

    lateinit var actionListener: IActionListener

    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    fun setup() {
        quoteView = layout.findViewById(R.id.main_quote) as TextView
        authorView = layout.findViewById(R.id.main_author) as TextView
        layout.findViewById(R.id.main_root).setOnClickListener { actionListener.displayQuote() }
    }

    fun showQuote(quote: String) {
        quoteView.text = quote
    }

    fun showAuthor(author: String) {
        authorView.text = author
    }
}