package io.github.dector.knight.repositories

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class CachedRepositoryTest {

    @Test fun alwaysFromCache() {
        // Given
        val mainRepo = MockRepository(listOf(TestData("Main One")))
        val cacheRepo = MockStorableRepository(listOf(TestData("Cache One"), TestData("Cache Two")))
        val cacheStrategy = AlwaysCachedDataCacheStrategy()
        val repository = CachedRepositoryImpl(mainRepo, cacheRepo, cacheStrategy)

        // When
        val size = repository.size()
        val data = repository.getAll()

        // Then
        assertEquals(size, cacheRepo.size())
        assertEquals(data, cacheRepo.getAll())
    }

    @Test fun alwaysFromMain() {
        // Given
        val mainRepo = MockRepository(listOf(TestData("Main One")))
        val cacheRepo = MockStorableRepository(listOf(TestData("Cache One"), TestData("Cache Two")))
        val cacheStrategy = AlwaysActualDataCacheStrategy()
        val repository = CachedRepositoryImpl(mainRepo, cacheRepo, cacheStrategy)

        // When
        val size = repository.size()
        val data = repository.getAll()

        // Then
        assertEquals(size, mainRepo.size())
        assertEquals(data, mainRepo.getAll())
    }
}
