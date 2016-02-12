package io.github.dector.quotes.qoutes.storage

import android.content.Context
import io.github.dector.quotes.qoutes.model.Quote
import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.meta.EntityModelBuilder
import io.requery.rx.RxSupport
import io.requery.sql.EntityDataStore

interface IQuotesStorage {

    fun getCount(): Int

    operator fun get(index: Int): Quote?
}

/*class MockQuotesStorage : IQuotesStorage {

    private val quotes = arrayOf(
            Quote("We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.", "Carl Sagan"),
            Quote("Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.", "Albert Einstein"),
            Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
            Quote("Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.", "Albert Einstein"),
            Quote("All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.", "Roger Bacon"),
            Quote("Equipped with his five senses, man explores the universe around him and calls the adventure Science.", "Edwin Powell Hubble")
    )

    override fun getCount() = quotes.size

    override operator fun get(index: Int): Quote? = when (index) {
        in 0..quotes.size -> quotes[index]
        else -> null
    }
}*/

class DatabaseQuotesStorage(context: Context) : IQuotesStorage {

    private val data = RxSupport.toReactiveStore(EntityDataStore<QuoteDbModel>(
            DatabaseSource(context, Models.DEFAULT, "db", 1).configuration))

    override fun getCount(): Int {
        return data.count(QuoteDbModel::class.java).get().value()
    }

    override fun get(index: Int): Quote? {
        return data.select(QuoteDbModel::class.java).get().first().toModel()
    }

    fun save(quote: Quote) {
        data.insert(quote.toDbModel())
    }
}

fun QuoteDbModel.toModel() = Quote(quote = this.text, author = this.author)

fun Quote.toDbModel() = QuoteDbModel(text = this.quote, author = this.author)

@Entity
open class QuoteDbModel(@JvmField var text: String = "",
                        @JvmField var author: String = "") {
}
