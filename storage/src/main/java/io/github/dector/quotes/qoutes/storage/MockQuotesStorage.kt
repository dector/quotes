package io.github.dector.quotes.qoutes.storage

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage

class MockQuotesStorage : IStorage<Quote?> {

    private val quotes = arrayOf(
            io.github.dector.quotes.domain.Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
            io.github.dector.quotes.domain.Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
            io.github.dector.quotes.domain.Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
            io.github.dector.quotes.domain.Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
            io.github.dector.quotes.domain.Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
            io.github.dector.quotes.domain.Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
    )

    override fun count() = quotes.size

    override operator fun get(index: Int) = when (index) {
        in 0..quotes.size -> quotes[index]
        else -> null
    }
}
