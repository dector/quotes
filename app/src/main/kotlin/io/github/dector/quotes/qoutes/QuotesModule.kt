package io.github.dector.quotes.qoutes

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.qoutes.model.DataProducer
import io.github.dector.quotes.R
import io.github.dector.quotes.qoutes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.qoutes.presentation.view.QuotesView

@Module
class QuotesModule() {

    @Provides
    fun dataProducer() = DataProducer()

    @Provides
    fun quotesPresenter(dataProducer: DataProducer) = QuotesPresenter(dataProducer)

    @Provides
    fun quotesView(context: Context) = QuotesView(LayoutInflater.from(context).inflate(R.layout.view_quotes, null))

}