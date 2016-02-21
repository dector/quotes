package io.github.dector.quotes.repositories

interface IRepository<Data, Criteria> {

    fun count(criteria: Criteria): Long

    fun get(criteria: Criteria): List<Data>
}