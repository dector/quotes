package io.github.dector.quotes.android.usecases

import android.os.Handler
import android.util.Log
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorage
import io.github.dector.quotes.usecases.IQuotesUseCase
import java.io.IOException
import java.util.*

class AsyncQuotesUseCase(val storage: IStorage<Quote>) : IQuotesUseCase {

    val random = Random()

    override fun getRandomQuote(callback: (Quote?) -> Unit) {
        val handler = Handler()

        Thread {
            try {
                val count = storage.count()

                if (count > 0) {
                    val index = random.nextInt()
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