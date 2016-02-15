package io.github.dector.quotes.qoutes.storage

interface IStorage<Data> {

    fun random(): Data?
}