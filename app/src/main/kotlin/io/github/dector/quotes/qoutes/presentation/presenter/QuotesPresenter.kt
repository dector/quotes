package io.github.dector.quotes.qoutes.presentation.presenter

import io.github.dector.quotes.common.onNext
import io.github.dector.quotes.qoutes.model.Quote
import io.github.dector.quotes.qoutes.presentation.ColorPair
import io.github.dector.quotes.qoutes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.qoutes.presentation.view.QuotesView
import io.github.dector.quotes.qoutes.storage.IStorage
import rx.subjects.BehaviorSubject

class QuotesPresenter(val quotesStorage: IStorage<Quote>, val colorStorage: IStorage<ColorPair>) : IQuotesPresenter, IQuotesActionListener {

    lateinit var view: QuotesView

    val quotesObservable: BehaviorSubject<Quote> = BehaviorSubject.create()
    val colorsObservable: BehaviorSubject<ColorPair> = BehaviorSubject.create()

    val dataSubject: BehaviorSubject<Unit> = BehaviorSubject.create()

    fun init() {
        view.init()

        quotesObservable
                .doOnNext { quote ->
                    view.showDataState()
                    view.showAuthor(quote.author)
                    view.showQuote(quote.quote)
                }
                .doOnError { view.showNoDataState() }
                .doOnCompleted { view.showNoDataState() }
                .subscribe()

        colorsObservable
                .doOnNext { colors ->
                    view.textColor(colors.text)
                    view.backgroundColor(colors.background)
                }
                .subscribe()

        dataSubject.doOnNext {
            val quote = quotesStorage.random()
            if (quote != null)
                quotesObservable.onNext(quote)
            else
                quotesObservable.onError(Throwable())

            val color = colorStorage.random()
            if (color != null)
                colorsObservable.onNext(color)
            else
                quotesObservable.onError(Throwable())
        }.subscribe()

        displayQuote()
    }

    override fun displayQuote() {
        dataSubject.onNext()
    }
}