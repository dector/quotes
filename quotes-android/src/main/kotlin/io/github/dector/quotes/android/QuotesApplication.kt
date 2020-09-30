package io.github.dector.quotes.android

import android.app.Application
import io.github.dector.quotes.android.di.appModule
import io.github.dector.quotes.android.di.everythingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class QuotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@QuotesApplication)
            modules(appModule(), everythingModule())
        }
    }
}

object ApiConfiguration {

    val USE_LOCAL = false //|| BuildConfig.DEBUG
    val LOCAL_BASE_URL = "http://10.12.1.103:1304/api/v1/"
    val PROD_BASE_URL = "http://smart-quotes.herokuapp.com/api/v1/"

    val BASE_URL = if (USE_LOCAL) LOCAL_BASE_URL else PROD_BASE_URL
}

/*
fun retrofit(): Retrofit
    = Retrofit.Builder()
    .baseUrl(ApiConfiguration.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()*/
