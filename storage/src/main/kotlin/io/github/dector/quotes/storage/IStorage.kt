package io.github.dector.quotes.storage

import io.github.dector.quotes.repositories.IRepository

interface IStorage<Data> {

    fun save(criteria: ListCriteria, data: Data): Boolean

    fun save(criteria: ListCriteria, data: List<Data>): Boolean

    fun remove(criteria: ListCriteria): Boolean
}

interface IStorableRepository<Data> : IStorage<Data>, IRepository<Data>