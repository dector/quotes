package io.github.dector.quotes.qoutes.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import io.github.dector.quotes.QuotesApplication
import io.github.dector.quotes.R
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
        setContentView(R.layout.activity_main)

        QuotesApplication.component.inject(this)

        val root = findViewById(R.id.root) as ViewGroup

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

