package io.github.dector.quotes

import android.app.Application
import dagger.Component
import javax.inject.Singleton

class QuotesApplication : Application() {

    companion object {
        @JvmStatic lateinit public var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .quotesModule(QuotesModule(this))
                .build()
        component.inject(this)
    }
}

@Singleton
@Component(modules = arrayOf(QuotesModule::class))
interface ApplicationComponent {

    fun inject(app: QuotesApplication)

    fun inject(activity: MainActivity)
}