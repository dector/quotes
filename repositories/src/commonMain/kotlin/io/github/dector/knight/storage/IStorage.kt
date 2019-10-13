package io.github.dector.knight.storage

import io.github.dector.knight.repositories.IRepository

interface IStorableRepository<Data> : IStorage<Data>, IRepository<Data>
