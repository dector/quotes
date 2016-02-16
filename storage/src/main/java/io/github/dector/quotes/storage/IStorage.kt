package io.github.dector.quotes.storage

interface IStorage<Data> {

    fun count(): Int

    operator fun get(index: Int): Data
}