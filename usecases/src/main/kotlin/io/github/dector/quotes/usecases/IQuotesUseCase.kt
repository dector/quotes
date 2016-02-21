package io.github.dector.quotes.usecases

import io.github.dector.quotes.domain.Quote

interface IQuoteUseCase : IUseCase<Quote>

interface IGetRandomQuoteUseCase : IQuoteUseCase