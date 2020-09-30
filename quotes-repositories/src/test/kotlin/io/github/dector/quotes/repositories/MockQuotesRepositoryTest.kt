package io.github.dector.quotes.repositories

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class MockQuotesRepositoryTest {

    @Test fun count_Anything() {
        // Given
        val repository = MockQuotesRepository()

        // When
        val result = repository.size()

        // Then
        assertEquals(result, 6)
    }

    @Test fun get_Anything() {
        // Given
        val repository = MockQuotesRepository()

        // When
        val result = repository.getAll()

        // Then
        assertEquals(result.size, 6)
    }
}