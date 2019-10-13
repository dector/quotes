package io.github.dector.quotes.android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.dector.quotes.android.QuotesApplication
import io.github.dector.quotes.android.common.fullscreen
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.presentation.presenter.QuotesPresenter
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

