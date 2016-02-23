package io.github.dector.quotes.storage

import io.github.dector.knight.storage.IStorableRepository
import io.github.dector.quotes.domain.Quote
import java.util.*

interface IStorableQuotesRepository : IStorableRepository<Quote>

class ListStorableQuotesRepository : IStorableQuotesRepository {

    val list = ArrayList<Quote>()

    override fun size() = list.size.toLong()

    override fun getAll() = list.toList()

    override fun save(data: Quote) = list.add(data)

    override fun saveAll(data: List<Quote>) = list.addAll(data)

    override fun remove(data: Quote) = list.remove(data)

    override fun removeAll(): Boolean { list.clear(); return true }
}