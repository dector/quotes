package io.github.dector.quotes.android.repositories

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria
import io.github.dector.quotes.storage.ListCriteria
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

class RetrofitQuotesRepository(val retrofit: Retrofit) : IQuotesRepository {

    val service by lazy { retrofit.create(QuotesService::class.java) }

    override fun count(criteria: ListCriteria): Long {
        return get(criteria).size.toLong()
    }

    override fun get(criteria: ListCriteria): List<Quote> {
        return when (criteria) {
            is ListCriteria.All -> service.quotes().execute().body()
            else -> emptyList()
        }
    }
}

interface QuotesService {

    @GET("quotes")
    fun quotes(): Call<List<Quote>>
}