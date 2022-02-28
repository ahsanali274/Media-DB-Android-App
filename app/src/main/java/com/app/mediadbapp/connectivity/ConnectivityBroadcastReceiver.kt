package com.app.mediadbapp.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber


abstract class ConnectivityBroadcastReceiver : BroadcastReceiver() {
    companion object {

        @JvmStatic
        fun registerToActivity(
            activity: AppCompatActivity,
            connectionBroadcastReceiver: ConnectivityBroadcastReceiver
        ) {
            val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
            activity.registerReceiver(connectionBroadcastReceiver, intentFilter)
            activity.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    Timber.d("----- onDestroy called")
                    activity.unregisterReceiver(connectionBroadcastReceiver)
                }
            })
        }

        @JvmStatic
        fun registerToFragment(
            context: Context,
            fragment: Fragment,
            connectionBroadcastReceiver: ConnectivityBroadcastReceiver
        ) {
            val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
            fragment.activity?.registerReceiver(connectionBroadcastReceiver, intentFilter)
            fragment.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    fragment.activity?.unregisterReceiver(connectionBroadcastReceiver)
                }
            })
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val hasConnection =
            !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)

        onConnectionChanged(hasConnection)
    }

    abstract fun onConnectionChanged(hasConnection: Boolean)
}
