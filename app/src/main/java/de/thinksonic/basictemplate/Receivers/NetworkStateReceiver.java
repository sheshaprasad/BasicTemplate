package de.thinksonic.basictemplate.Receivers;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:11
    For Project : BasicTemplate
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import de.thinksonic.basictemplate.app_initializer.AppInit;

public class NetworkStateReceiver extends BroadcastReceiver {

    protected Boolean connected;

    public NetworkStateReceiver() {
        connected = null;
    }

    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getExtras() == null)
            return;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo ni = manager.getActiveNetworkInfo();

        if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
            AppInit.dismissNA();
        } else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            connected = false;
            AppInit.showNA();
        }
    }

}