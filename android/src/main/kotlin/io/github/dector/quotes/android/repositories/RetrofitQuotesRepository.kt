package io.github.dector.quotes.android.repositories

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.repositories.IQuotesRepository
import io.github.dector.quotes.repositories.QuotesCriteria
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

class RetrofitQuotesRepository(val retrofit: Retrofit) : IQuotesRepository {

    val service by lazy { retrofit.create(QuotesService::class.java) }

    override fun count(criteria: QuotesCriteria): Long {
        return get(criteria).size.toLong()
    }

    override fun get(criteria: QuotesCriteria): List<Quote> {
        return when (criteria) {
            is QuotesCriteria.Anything -> service.quotes().execute().body()
        }
    }
}

interface QuotesService {

    @GET("quotes")
    fun quotes(): Call<List<Quote>>
}