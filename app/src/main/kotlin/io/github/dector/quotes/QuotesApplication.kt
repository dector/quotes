package io.github.dector.quotes

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.dector.quotes.qoutes.QuotesModule
import io.github.dector.quotes.qoutes.presentation.QuotesActivity
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
        component.inject(this)
    }
}

@Singleton
@Component(modules = arrayOf(
        AppModule::class, QuotesModule::class))
interface ApplicationComponent {

    fun inject(app: QuotesApplication)

    fun inject(activity: QuotesActivity)
}

@Module
class AppModule(val app: QuotesApplication) {

    @Provides
    fun context(): Context = app
}