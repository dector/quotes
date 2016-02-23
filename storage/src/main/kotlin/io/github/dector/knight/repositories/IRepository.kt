package io.github.dector.knight.repositories

interface IRepository<Data> {

    fun size(): Long

    fun getAll(): List<Data>
}