package de.thinksonic.basictemplate.Interface;

import com.android.volley.VolleyError;
import org.json.JSONObject;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:11
    For Project : BasicTemplate
*/

import de.thinksonic.basictemplate.Tools.RequestTypes;

public interface VolleyHandler {

    void onSuccess(RequestTypes requestTypes, JSONObject jsonObject);

    void onError(RequestTypes requestTypes, VolleyError volleyError);

}
