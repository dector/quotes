package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository

class MockQuotesRepository(val data: List<Quote> = emptyList()) : IQuotesRepository {

    override fun size() = data.size.toLong()

    override fun getAll() = data
}

class AsDesignedThrowable : Throwable("I was born to be thrown!")

class ErrorQuotesRepository(val error: Throwable = AsDesignedThrowable()) : IQuotesRepository {

    override fun size() = throw error

    override fun getAll() = throw error
}