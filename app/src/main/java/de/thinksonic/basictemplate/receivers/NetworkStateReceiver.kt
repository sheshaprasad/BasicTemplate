package de.thinksonic.basictemplate.receivers

/**
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:11
    For Project : BasicTemplate
*/

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

import de.thinksonic.basictemplate.app_initializer.AppInit

class NetworkStateReceiver : BroadcastReceiver() {

    protected var connected: Boolean? = null

    init {
        connected = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null || intent.extras == null)
            return

        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = manager.activeNetworkInfo

        if (ni != null && ni.state == NetworkInfo.State.CONNECTED) {
            connected = true
            AppInit.dismissNA(context)
        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, java.lang.Boolean.FALSE)) {
            connected = false
            AppInit.showNA(context)
        }
    }

}