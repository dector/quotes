package io.github.dector.quotes.storage

import io.github.dector.quotes.domain.Quote
import java.util.*

interface IStorableQuotesRepository : IStorableRepository<Quote>

// FIXME use generics (how ?)
sealed class ListCriteria {
    class All : ListCriteria()
    class Indexed(val index: Int) : ListCriteria()
}

class ListStorableQuotesRepository : IStorableQuotesRepository {

    val list = ArrayList<Quote>()

    override fun count(criteria: ListCriteria): Long {
        return when (criteria) {
            is ListCriteria.All -> list.size.toLong()
            is ListCriteria.Indexed -> if (criteria.isInRange()) 1 else 0
        }
    }

    override fun get(criteria: ListCriteria): List<Quote> {
        return when (criteria) {
            is ListCriteria.All -> list.toList()
            is ListCriteria.Indexed -> if (criteria.isInRange()) listOf(list[criteria.index]) else emptyList()
        }
    }

    override fun save(criteria: ListCriteria, data: Quote): Boolean {
        return when (criteria) {
            is ListCriteria.All -> list.add(data)
            is ListCriteria.Indexed -> if (criteria.isInRange()) { list.add(criteria.index, data); true } else false
        }
    }

    override fun save(criteria: ListCriteria, data: List<Quote>): Boolean {
        return when (criteria) {
            is ListCriteria.All -> list.addAll(data)
            is ListCriteria.Indexed -> if (criteria.isInRange()) { list.addAll(criteria.index, data); true } else false
        }
    }

    override fun remove(criteria: ListCriteria): Boolean {
        return when (criteria) {
            is ListCriteria.All -> { list.clear(); true }
            is ListCriteria.Indexed -> if (criteria.isInRange()) { list.removeAt(criteria.index); true } else false
        }
    }

    private inline fun ListCriteria.Indexed.isInRange() = this.index in 1..list.size
}