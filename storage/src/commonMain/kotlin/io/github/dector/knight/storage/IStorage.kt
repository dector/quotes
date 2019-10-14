package io.github.dector.knight.storage

@Deprecated("")
interface IStorage<Data> {

    fun saveAll(data: List<Data>): Boolean

    fun removeAll(): Boolean
}
