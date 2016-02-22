package io.github.dector.quotes.repositories

import io.github.dector.quotes.storage.ListCriteria

interface IRepository<Data> {

    fun count(criteria: ListCriteria): Long

    fun get(criteria: ListCriteria): List<Data>
}