package io.github.dector.quotes.android

import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.R
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.domain.Palette
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.view.ColorPair
import io.github.dector.quotes.presentation.view.asColorCouple
import io.github.dector.quotes.repositories.RandomColorsRepository
import io.github.dector.quotes.repositories.RandomQuoteRepository
import io.github.dector.quotes.repositories.RealRandomColorsRepository
import io.github.dector.quotes.repositories.RealRandomQuoteRepository
import io.github.dector.quotes.storage.InMemoryQuotesStorage
import io.github.dector.quotes.storage.QuotesStorage
import javax.inject.Singleton

@Module
class QuotesModule() {

    @Provides
    fun storage(): QuotesStorage =
        InMemoryQuotesStorage().apply {
            preStoredQuotesData().forEach(this::insert)
        }

    @Provides
    @Singleton
    fun randomQuoteRepository(storage: QuotesStorage): RandomQuoteRepository =
        RealRandomQuoteRepository(storage)

    @Provides
    @Singleton
    fun randomColorsRepository(): RandomColorsRepository =
        RealRandomColorsRepository(
            palette = Palette(ColorPairProvider.colors.map(ColorPair::asColorCouple)),
            initialValue = ColorPairProvider.colors.last().asColorCouple()
        )

    @Provides fun quotesView(inflater: LayoutInflater)
            = QuotesView(inflater.inflate(R.layout.view_quotes, null))
}

private fun preStoredQuotesData() = listOf(
    Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
    Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
    Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
    Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
    Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
    Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
)
