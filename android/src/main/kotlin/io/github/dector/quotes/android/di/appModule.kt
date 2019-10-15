package io.github.dector.quotes.android.di

import android.content.Context
import android.net.ConnectivityManager
import io.github.dector.quotes.android.common.network.AndroidNetworkManager
import io.github.dector.quotes.android.common.network.NetworkManager
import org.koin.dsl.module

internal fun appModule() = module {
    factory<NetworkManager> {
        AndroidNetworkManager(
            get<Context>().connectivityManager()
        )
    }
}

private fun Context.connectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
