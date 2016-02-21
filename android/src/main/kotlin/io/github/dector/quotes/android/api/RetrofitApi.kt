package io.github.dector.quotes.android.api

import io.github.dector.quotes.domain.Quote
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitApi : IApi {

    private val USE_LOCAL = false
    private val LOCAL_BASE_URL = "http://10.12.1.103:1304/"
    private val PROD_BASE_URL = "http://smart-quotes.herokuapp.com/"

    private val BASE_URL = if (USE_LOCAL) LOCAL_BASE_URL else PROD_BASE_URL

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