package io.github.dector.quotes.android.usecases

import android.os.Handler
import android.util.Log
import io.github.dector.quotes.common.randomUntil
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage
import io.github.dector.quotes.usecases.IQuotesUseCase
import java.io.IOException

class AsyncQuotesUseCase(val storage: IStorage<Quote>) : IQuotesUseCase {

    override fun getRandomQuote(callback: (Quote?) -> Unit) {
        val handler = Handler()

        Thread {
            try {
                val count = storage.count()

                if (count > 0) {
                    val index = storage.count().randomUntil()
                    val result = storage[index]

                    handler.post { callback(result) }
                } else {
                    handler.post { callback(null) }
                }
            } catch (e: IOException) {
                Log.e("Quote loading", e.message, e)
                handler.post { callback(null) }
            }
        }.start()
    }
}