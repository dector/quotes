package io.github.dector.quotes.usecases

import io.github.dector.knight.usecases.IUseCase
import io.github.dector.quotes.domain.Quote

interface IQuoteUseCase : IUseCase<Quote, Throwable>
