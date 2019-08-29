package de.thinksonic.basictemplate.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NoConnectionError
import com.android.volley.Response
import com.android.volley.TimeoutError
import com.android.volley.toolbox.StringRequest
import de.thinksonic.basictemplate.interfaces.VolleyHandler
import de.thinksonic.basictemplate.R
import de.thinksonic.basictemplate.app_initializer.AppInit
import de.thinksonic.basictemplate.application_ui.SplashActivity
import org.json.JSONException
import org.json.JSONObject

/**
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:10
    For Project : BasicTemplate
*/

class VolleyClass(private val mContext: Context, private val mVolleyHandler: VolleyHandler) {

    fun PostCall(func: String, params: Map<String, String>, requestTypes: RequestTypes) {

        val url = mContext.resources.getString(R.string.server_id) + func + mContext.resources.getString(R.string.server_key)

        AppInit.stringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
            AppInit.stringRequest = null
            assert(response != null)
            try {

                val js = JSONObject(response!!)
                if (js.getString("result").equals("fail", ignoreCase = true)) {
                    try {
                        if (js.getString("reason").equals("Authentication Failure", ignoreCase = true)) {

                            val logOut = Intent(mContext, SplashActivity::class.java)
                            logOut.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            mContext.startActivity(logOut)
                            (mContext as Activity).finish()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
                mVolleyHandler.onSuccess(requestTypes, JSONObject(response))

            } catch (e: JSONException) {
                e.printStackTrace()

            }
        }, Response.ErrorListener { error ->
            error.printStackTrace()
            if (AppInit.ConntectedToInternet) {
                AppInit.stringRequest = null
            }
            mVolleyHandler.onError(requestTypes, error)
            if (error is TimeoutError || error is NoConnectionError) {
                Toast.makeText(mContext,
                        mContext.getString(R.string.error_network_timeout),
                        Toast.LENGTH_LONG).show()
            }
        }) {

            override fun getParams(): Map<String, String> {
                return params
            }
        }
        AppInit.stringRequest!!.retryPolicy = DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        AppInit.requestQueue.add(AppInit.stringRequest!!)
    }
}