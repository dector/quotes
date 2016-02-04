package io.github.dector.quotes

import dagger.Module
import dagger.Provides

@Module
class QuotesModule(val app: QuotesApplication) {

    @Provides
    fun dataProducer() = DataProducer()

    @Provides
    fun quotesPresenter(dataProducer: DataProducer) = Presenter(dataProducer)

}