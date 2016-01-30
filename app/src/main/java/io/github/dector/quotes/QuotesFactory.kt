package io.github.dector.quotes

import java.util.*

class QuotesFactory {

    private val random = Random()
    private var lastQuote: Quote? = null

    private val quotes = arrayOf(
            Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
            Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
            Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
            Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
            Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
            Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
    )

    fun randomQuote(): Quote {
        var newQuote = random()
        while (newQuote == lastQuote)
            newQuote = random()

        lastQuote = newQuote
        return newQuote
    }

    private fun random() = quotes[random.nextInt(quotes.size)]
}

data class Quote(val quote: String, val author: String)