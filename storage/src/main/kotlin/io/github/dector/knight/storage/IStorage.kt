package io.github.dector.knight.storage

import io.github.dector.knight.repositories.IRepository

interface IStorage<Data> {

    fun save(data: Data): Boolean

    fun saveAll(data: List<Data>): Boolean

    fun remove(data: Data): Boolean

    fun removeAll(): Boolean
}

interface IStorableRepository<Data> : IStorage<Data>, IRepository<Data>