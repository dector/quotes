package io.github.dector.quotes.android.api

import io.github.dector.quotes.domain.Quote

interface IApi {

    fun quotes(): List<Quote>
}