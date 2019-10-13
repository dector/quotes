package io.github.dector.knight.storage

@Deprecated("")
interface IStorage<Data> {

    fun save(data: Data): Boolean

    fun saveAll(data: List<Data>): Boolean

    fun remove(data: Data): Boolean

    fun removeAll(): Boolean
}
