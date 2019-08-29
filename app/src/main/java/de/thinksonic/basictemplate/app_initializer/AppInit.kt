package de.thinksonic.basictemplate.app_initializer

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.WindowManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import de.thinksonic.basictemplate.interfaces.ActivityCreated
import de.thinksonic.basictemplate.interfaces.VolleyHandler
import de.thinksonic.basictemplate.receivers.NetworkStateReceiver
import de.thinksonic.basictemplate.tools.UserPreference
import de.thinksonic.basictemplate.tools.Utils
import de.thinksonic.basictemplate.tools.VolleyClass

/**
 * Created By : Shesha Vasukhi Prasad
 * Date : 15-Jul-2019
 * Time : 22:12
 * For Project : BasicTemplate
*/


class AppInit : Application() {
    lateinit var serverCall: VolleyClass
    private var networkStateReceiver: NetworkStateReceiver? = null
    private var mActivity: Activity? = null
    internal lateinit var userPreference: UserPreference

    override fun onCreate() {
        super.onCreate()

        requestQueue = Volley.newRequestQueue(this)
        userPreference = UserPreference(this)
        networkStateReceiver = NetworkStateReceiver()

        registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity,
                                           savedInstanceState: Bundle?) {

                mActivity = activity
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
                activity.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
                serverCall = VolleyClass(activity, activity as VolleyHandler)


            }

            override fun onActivityStarted(activity: Activity) {
                (activity as ActivityCreated).initViews()
            }

            override fun onActivityResumed(activity: Activity) {
                if (networkStateReceiver != null) {
                    registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
                } else {
                    networkStateReceiver = NetworkStateReceiver()
                    registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
                }
            }

            override fun onActivityPaused(activity: Activity) {
                unregisterReceiver(networkStateReceiver)
            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {

            }
        })

    }

    companion object {

        var ConntectedToInternet: Boolean = false
        var SplashCreated = false
        lateinit var requestQueue: RequestQueue
        var stringRequest: StringRequest? = null

        fun showNA(context: Context) {
            ConntectedToInternet = false
            Utils.ShowNetworkAlert(context as Activity)
        }

        fun dismissNA(context: Context) {
            ConntectedToInternet = true
            Utils.DismissNetworkAlert(context as Activity)
        }
    }
}
