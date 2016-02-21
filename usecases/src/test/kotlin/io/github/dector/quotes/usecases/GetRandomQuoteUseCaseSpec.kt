package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.equalTo
import io.github.dector.quotes.nonNull
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria
import org.jetbrains.spek.api.Spek

class GetRandomQuoteUseCaseSpec : Spek() { init {

    given("a use case") {
        val repository = MockQuotesRepository()

        val useCase = GetRandomQuoteUseCase(repository, { if (this.size > 0) this[1] else null })

        on("non empty repository") {
            repository.data = listOf3

            useCase.execute { result ->

                it("should return concrete value") {
                    val quote = listOf3[1]

                    result.nonNull() &&
                    result?.quote equalTo quote.quote &&
                    result?.author equalTo quote.author
                }
            }
        }

        on("empty repository") {
            repository.data = listOf0

            useCase.execute { result ->

                it("should return null value") {
                    result == null
                }
            }
        }
    }
}}

class MockQuotesRepository(var data: List<Quote> = emptyList()) : IQuotesRepository {

    override fun count(criteria: QuotesCriteria) = data.size.toLong()

    override fun get(criteria: QuotesCriteria) = data
}

val listOf0 = emptyList<Quote>()

val listOf3 = listOf(
        Quote("Quote #1", "Author #1"),
        Quote("Quote #2", "Author #2"),
        Quote("Quote #3", "Author #3"))