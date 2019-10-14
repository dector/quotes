package io.github.dector.quotes.android.repositories

import android.content.SharedPreferences
import io.github.dector.quotes.common.toJsonString
import io.github.dector.quotes.common.toQuotesList
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorableQuotesRepository

class FileStorableQuotesRepository(val sharedPreferences: SharedPreferences) : IStorableQuotesRepository {

    private val STORAGE_KEY = "KEY_QUOTES"

    override fun size() = getAll().size.toLong()

    override fun getAll() = sharedPreferences
        .getString(STORAGE_KEY, "[]")
        ?.toQuotesList()
        .orEmpty()

    override fun saveAll(data: List<Quote>) = sharedPreferences
            .edit()
            .putString(STORAGE_KEY, data.toJsonString())
            .commit()

    override fun removeAll() = sharedPreferences.edit().clear().commit()
}
