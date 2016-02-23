package io.github.dector.quotes.android.repositories

import android.content.SharedPreferences
import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.storage.IStorableQuotesRepository
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class FileStorableQuotesRepository(val sharedPreferences: SharedPreferences) : IStorableQuotesRepository {

    private val STORAGE_KEY = "KEY_QUOTES"

    override fun size() = getAll().size.toLong()

    override fun getAll() = sharedPreferences
            .getString(STORAGE_KEY, "[]")
            .toQuotesList()

    override fun save(data: Quote): Boolean {
        throw UnsupportedOperationException()
    }

    override fun saveAll(data: List<Quote>) = sharedPreferences
            .edit()
            .putString(STORAGE_KEY, data.toJsonString())
            .commit()

    override fun remove(data: Quote): Boolean {
        throw UnsupportedOperationException()
    }

    override fun removeAll() = sharedPreferences.edit().clear().commit()
}

fun String.toQuotesList(): List<Quote> {
    val json = try { JSONArray(this) } catch (e: JSONException) { JSONArray() }

    val list = ArrayList<Quote>(json.length())

    for (i in 0..json.length()) {
        list[i] = try { json.getJSONObject(i) } catch (e: JSONException) { JSONObject() }.toQuote()
    }

    return list
}

fun JSONObject.toQuote() = Quote(quote = this.optString("quote"), author = this.optString("author"))

fun List<Quote>.toJsonString(): String = this.map(Quote::toJson).toArray().toString()

fun Quote.toJson(): JSONObject = JSONObject().put("quote", this.quote).put("author", this.author)

fun List<JSONObject>.toArray(): JSONArray = this.fold(JSONArray()) { array, item -> array.put(item) }