package de.thinksonic.basictemplate.application_ui

import android.os.Bundle
import android.view.View

import com.android.volley.VolleyError

import org.json.JSONObject

import de.thinksonic.basictemplate.tools.Utils
import de.thinksonic.basictemplate.app_initializer.BaseActivity
import de.thinksonic.basictemplate.R
import de.thinksonic.basictemplate.tools.RequestTypes

/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:52
    For Project : BasicTemplate
*/

class ChooseActivity() : BaseActivity() {

    lateinit var parentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
    }

    override fun initViews() {

        parentView = findViewById(R.id.parentView)
    }

    override fun onSuccess(requestTypes: RequestTypes, jsonObject: JSONObject) {

    }

    override fun onError(requestTypes: RequestTypes, volleyError: VolleyError) {

    }

    fun SubmitClicked(v: View) {
        Utils.SnackBar(this, "Button Click! This is a Custom Snackbar", parentView)
    }
}
