package io.github.dector.quotes.repositories

import io.github.dector.quotes.domain.Quote

class MockQuotesRepository : IQuotesRepository {

    private val data = arrayOf(
            Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
            Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
            Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
            Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
            Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
            Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
    )

    override fun count(criteria: QuotesCriteria): Long {
        return when (criteria) {
            is QuotesCriteria.Anything -> data.size.toLong()
        }
    }

    override fun get(criteria: QuotesCriteria): List<Quote> {
        return when (criteria) {
            is QuotesCriteria.Anything -> data.asList()
        }
    }
}