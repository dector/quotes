package io.github.dector.knight.repositories

interface IRepository<Data> : IAvailable {

    fun size(): Long

    fun getAll(): List<Data>
}

interface IAvailable {

    fun isAvailable(): Boolean = true
}