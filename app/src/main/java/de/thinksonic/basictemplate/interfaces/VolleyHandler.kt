package de.thinksonic.basictemplate.interfaces

import com.android.volley.VolleyError
import org.json.JSONObject


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:11
    For Project : BasicTemplate
*/

import de.thinksonic.basictemplate.tools.RequestTypes

interface VolleyHandler {

    fun onSuccess(requestTypes: RequestTypes, jsonObject: JSONObject)

    fun onError(requestTypes: RequestTypes, volleyError: VolleyError)

}
