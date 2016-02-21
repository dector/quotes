package io.github.dector.quotes.android.api

import io.github.dector.quotes.domain.Quote
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitApi : IApi {

    private val BASE_URL = "http://10.12.1.103:1304/"

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val quotesService = retrofit.create(QuotesService::class.java)

    override fun quotes() = quotesService.quotes().execute().body()
}

interface QuotesService {

    @GET("quotes")
    fun quotes(): Call<List<Quote>>
}