package io.github.dector.quotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import rx.Subscriber
import rx.lang.kotlin.observable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: Presenter

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QuotesApplication.component.inject(this)

        val root = findViewById(R.id.root) as ViewGroup

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

class DataProducer {

    private var subscriber: Subscriber<in Pair<Quote, ColorPair>>? = null

    private val quotes = QuotesFactory()
    private val palette = Palette()

    val observable = observable<Pair<Quote, ColorPair>> { subscriber ->
        this.subscriber = subscriber
        next()
    }

    fun next() {
        val subscriber = this.subscriber ?: return

        if (!subscriber.isUnsubscribed)
            subscriber.onNext(Pair(quotes.randomQuote(), palette.random()))
    }
}

interface IActionListener {

    fun displayQuote()
}

class Presenter(val dataProducer: DataProducer) : IActionListener {

    lateinit var view: View

    fun init() {
        view.setup()

        dataProducer.observable.subscribe { displayQuote(it.first, it.second) }
    }

    override fun displayQuote() {
        dataProducer.next()
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