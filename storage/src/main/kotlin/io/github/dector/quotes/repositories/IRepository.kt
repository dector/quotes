package io.github.dector.quotes.repositories

interface IRepository<Data> {

    fun size(): Long

    fun getAll(): List<Data>
}