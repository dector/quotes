package io.github.dector.quotes.storage

interface IStorage<Data> {

    fun count(): Int

    fun all(): List<Data>

    operator fun get(index: Int): Data?
}