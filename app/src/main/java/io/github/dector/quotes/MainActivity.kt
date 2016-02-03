package io.github.dector.quotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import rx.Observable
import rx.lang.kotlin.observable

class MainActivity : AppCompatActivity() {

    lateinit var presenter: Presenter
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById(R.id.root) as ViewGroup

        val observable = observable<Pair<Quote, ColorPair>> { subscriber ->
            val quotes = QuotesFactory()
            val palette = Palette()

            subscriber.onNext(Pair(quotes.randomQuote(), palette.random()))
        }

        presenter = Presenter(observable)
        view = View(LayoutInflater.from(this).inflate(R.layout.view_main, root, false))

        root.removeAllViews()
        root.addView(view.layout)

        presenter.view = view
        view.actionListener = presenter

        presenter.init()
    }

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                android.view.View.SYSTEM_UI_FLAG_FULLSCREEN or
                android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}

interface IActionListener {

    fun displayQuote()
}

class Presenter(val observable: Observable<Pair<Quote, ColorPair>>) : IActionListener {

    lateinit var view: View

    fun init() {
        view.setup()

        observable.subscribe { displayQuote(it.first, it.second) }
    }

    override fun displayQuote() {

    }

    fun displayQuote(quote: Quote, colors: ColorPair) {
        view.showAuthor(quote.author)
        view.showQuote(quote.quote)

        view.textColor(colors.text)
        view.backgroundColor(colors.background)
    }
}

class View(val layout: android.view.View) {

    lateinit var actionListener: IActionListener

    private lateinit var rootView: android.view.View
    private lateinit var touchView: android.view.View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    fun setup() {
        rootView = layout.findViewById(R.id.main_root)
        touchView = layout.findViewById(R.id.main_touch)
        quoteView = layout.findViewById(R.id.main_quote) as TextView
        authorView = layout.findViewById(R.id.main_author) as TextView

        touchView.setOnClickListener { actionListener.displayQuote() }
    }

    fun showQuote(quote: String) {
        quoteView.text = quote
    }

    fun showAuthor(author: String) {
        authorView.text = author
    }

    fun textColor(color: Color) {
        quoteView.setTextColor(color.solidValue())
        authorView.setTextColor(color.solidValue())
    }

    fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solidValue())
    }
}