package com.app.mediadbapp.baseClasses

import androidx.appcompat.app.AppCompatActivity
import com.app.mediadbapp.connectivity.ConnectivityBroadcastReceiver

abstract class BaseActivity : AppCompatActivity() {

    fun isShowing(): Boolean {
        return window.decorView.isShown
    }

    protected fun registerConnectivityBroadcastReceiver() {
        ConnectivityBroadcastReceiver.registerToActivity(
            this,
            object : ConnectivityBroadcastReceiver() {
                override fun onConnectionChanged(hasConnection: Boolean) {
                }
            })
    }
}