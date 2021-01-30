package com.example.foody.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class NetworkListener: ConnectivityManager.NetworkCallback() {

    private val isNetWorkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(context: Context): MutableStateFlow<Boolean>{

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(this)

        var isConneted = false

        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                    isConneted = true
                    return@forEach
                }
            }
        }

        isNetWorkAvailable.value = isConneted

        return isNetWorkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetWorkAvailable.value = true
    }

    override fun onLost(network: Network) {
        isNetWorkAvailable.value = false
    }
}