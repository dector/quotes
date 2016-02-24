package io.github.dector.quotes.android.network

import android.net.ConnectivityManager

class AndroidNetworkManager(val cm: ConnectivityManager) : INetworkManager {

    override fun isNetworkAvailable() = cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
}