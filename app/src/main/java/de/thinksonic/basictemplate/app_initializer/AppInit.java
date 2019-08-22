package de.thinksonic.basictemplate.app_initializer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import de.thinksonic.basictemplate.Interface.ActivityCreated;
import de.thinksonic.basictemplate.Interface.VolleyHandler;
import de.thinksonic.basictemplate.Receivers.NetworkStateReceiver;
import de.thinksonic.basictemplate.Tools.UserPreference;
import de.thinksonic.basictemplate.Tools.Utils;
import de.thinksonic.basictemplate.Tools.VolleyClass;

/*
  Created By : Shesha Vasukhi Prasad
  Date : 15-Jul-2019
  Time : 22:12
  For Project : BasicTemplate
*/


public class AppInit extends Application {

    public static boolean ConntectedToInternet;
    public static boolean SplashCreated = false;
    public VolleyClass serverCall;
    public static RequestQueue requestQueue;
    private NetworkStateReceiver networkStateReceiver;
    @SuppressLint("StaticFieldLeak")
    static Activity mActivity;
    UserPreference userPreference;
    public static StringRequest stringRequest;

    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = Volley.newRequestQueue(this);
        userPreference = new UserPreference(this);
        networkStateReceiver = new NetworkStateReceiver();

        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(@NonNull final Activity activity,
                                          Bundle savedInstanceState) {

                mActivity = activity;
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
                serverCall = new VolleyClass(activity,(VolleyHandler) activity);


            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                ((ActivityCreated)activity).initViews();
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                if(networkStateReceiver!=null) {
                    registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
                }else{
                    networkStateReceiver = new NetworkStateReceiver();
                    registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                unregisterReceiver(networkStateReceiver);
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity,@NonNull  Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });

    }

    public static void showNA(){
        ConntectedToInternet = false;
        Utils.ShowNetworkAlert(mActivity);
    }
    public static void dismissNA(){
        ConntectedToInternet = true;
        Utils.DismissNetworkAlert(mActivity);
    }
}
