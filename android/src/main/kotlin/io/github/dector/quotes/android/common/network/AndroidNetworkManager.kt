package io.github.dector.quotes.android.common.network

import android.net.ConnectivityManager

class AndroidNetworkManager(
    private val cm: ConnectivityManager
) : NetworkManager {

    override fun isNetworkAvailable() = cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
}
