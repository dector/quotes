package io.github.dector.quotes.qoutes.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.dector.quotes.QuotesApplication
import io.github.dector.quotes.common.fullscreen
import io.github.dector.quotes.qoutes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.qoutes.presentation.view.QuotesView
import javax.inject.Inject

class QuotesActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: QuotesPresenter

    @Inject
    lateinit var view: QuotesView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuotesApplication.component.inject(this)

        setContentView(view.content)

        presenter.view = view
        view.listener = presenter

        presenter.init()
    }

    override fun onResume() {
        super.onResume()
        fullscreen()
    }
}

