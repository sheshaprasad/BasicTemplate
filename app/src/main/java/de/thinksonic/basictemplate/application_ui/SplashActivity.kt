package de.thinksonic.basictemplate.application_ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle

import com.android.volley.VolleyError
import org.json.JSONObject

import de.thinksonic.basictemplate.app_initializer.BaseActivity
import de.thinksonic.basictemplate.R
import de.thinksonic.basictemplate.tools.RequestTypes

/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:07
    For Project : BasicTemplate
*/

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun initViews() {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    }

    override fun onSuccess(requestTypes: RequestTypes, jsonObject: JSONObject) {

    }

    override fun onError(requestTypes: RequestTypes, volleyError: VolleyError) {

    }

}
