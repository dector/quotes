package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote
import org.testng.Assert.*
import org.testng.annotations.Test

class GetRandomQuoteUseCaseTest {

    @Test fun nonEmptyData_providedRandomizer() {
        // Given
        val repository = MockQuotesRepository(listOf3)
        val useCase = GetRandomQuoteUseCase(repository, getSecond)

        // When
        useCase.execute ({ quote ->
            // Then
            assertNotNull(quote)

            val expected = listOf3[1]

            assertEquals(quote?.quote, expected.quote)
            assertEquals(quote?.author, expected.author)
        })
    }

    @Test fun emptyData_providedRandomizer() {
        // Given
        val repository = MockQuotesRepository()
        val useCase = GetRandomQuoteUseCase(repository, getNull)

        // When
        useCase.execute({ result ->
            // Then
            assertNull(result)
        })
    }

    @Test fun nonEmptyData_defaultRandomizer() {
        // Given
        val repository = MockQuotesRepository(listOf1)
        val useCase = GetRandomQuoteUseCase(repository)

        // When
        useCase.execute({ result ->
            // Then
            assertNotNull(result)

            val actual = result as Quote
            val expected = listOf1[0]

            assertEquals(actual.quote, expected.quote)
            assertEquals(actual.author, expected.author)
        })
    }

    @Test fun emptyData_defaultRandomizer() {
        // Given
        val repository = MockQuotesRepository()
        val useCase = GetRandomQuoteUseCase(repository)

        // When
        useCase.execute({ result ->
            // Then
            assertNull(result)
        })
    }
}

val getSecond: List<Quote>.() -> Quote? = { this[1] }

val getNull: List<Quote>.() -> Quote? = { null }

val listOf1 = listOf(Quote("Quote #1", "Author #1"))

val listOf3 = listOf(
        Quote("Quote #1", "Author #1"),
        Quote("Quote #2", "Author #2"),
        Quote("Quote #3", "Author #3"))