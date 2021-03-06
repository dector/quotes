package io.github.dector.quotes.android.repositories

import io.github.dector.quotes.domain.Quote
import retrofit2.Call
import retrofit2.http.GET

/*class RetrofitQuotesRepository(val retrofit: Retrofit, val networkManager: NetworkManager) : IQuotesRepository {

    val service by lazy { retrofit.create(QuotesService::class.java) }

    override fun size() = getAll().size.toLong()

    override fun getAll() = service.quotes().execute().body()

    override fun isAvailable() = networkManager.isNetworkAvailable()
}*/

interface QuotesService {

    @GET("quotes")
    fun quotes(): Call<List<Quote>>
}
