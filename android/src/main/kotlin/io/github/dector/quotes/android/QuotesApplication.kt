@file:JvmName("QuotesApplication")

package io.github.dector.quotes.android

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.android.presentation.QuotesActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class QuotesApplication : Application() {

    companion object {
        @JvmStatic lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .quotesModule(QuotesModule())
                .build()
    }
}

@Singleton
@Component(modules = arrayOf(
        AppModule::class, QuotesModule::class))
interface ApplicationComponent {

    fun inject(activity: QuotesActivity)
}

@Module
class AppModule(val app: QuotesApplication) {

    @Provides fun context(): Context
            = app

    @Provides fun layoutInflater(context: Context): LayoutInflater
            = LayoutInflater.from(context)

    @Provides fun retrofit(): Retrofit
            = Retrofit.Builder()
            .baseUrl(ApiConfiguration.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides fun connectivityManager(context: Context): ConnectivityManager
            = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides fun mainThreadHandler(): Handler
            = Handler(Looper.getMainLooper())
}

object ApiConfiguration {

    val USE_LOCAL = false //|| BuildConfig.DEBUG
    val LOCAL_BASE_URL = "http://10.12.1.103:1304/api/v1/"
    val PROD_BASE_URL = "http://smart-quotes.herokuapp.com/api/v1/"

    val BASE_URL = if (USE_LOCAL) LOCAL_BASE_URL else PROD_BASE_URL
}