package io.github.dector.quotes.qoutes

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.qoutes.model.DataProducer
import io.github.dector.quotes.R
import io.github.dector.quotes.qoutes.model.Quote
import io.github.dector.quotes.qoutes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.qoutes.presentation.view.QuotesView
import io.github.dector.quotes.qoutes.storage.DatabaseQuotesStorage
import io.github.dector.quotes.qoutes.storage.IQuotesStorage
//import io.github.dector.quotes.qoutes.storage.MockQuotesStorage

@Module
class QuotesModule() {

    @Provides
    fun quotesStorage(context: Context): IQuotesStorage {
        val storage = DatabaseQuotesStorage(context)//MockQuotesStorage()

        if (storage.getCount() == 0) {
            storage.save(Quote("First quote", "Useless"))
        }

        return storage
    }

    @Provides
    fun dataProducer(quotesStorage: IQuotesStorage) = DataProducer(quotesStorage)

    @Provides
    fun quotesPresenter(dataProducer: DataProducer) = QuotesPresenter(dataProducer)

    @Provides
    fun quotesView(context: Context) = QuotesView(LayoutInflater.from(context).inflate(R.layout.view_quotes, null))

}