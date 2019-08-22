package de.thinksonic.basictemplate.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import de.thinksonic.basictemplate.application_ui.SplashActivity;
import de.thinksonic.basictemplate.app_initializer.AppInit;
import de.thinksonic.basictemplate.Interface.VolleyHandler;
import de.thinksonic.basictemplate.R;


/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 23:10
    For Project : BasicTemplate
*/

public class VolleyClass {


    private VolleyHandler mVolleyHandler;
    private Context mContext;

    public VolleyClass(Context context, VolleyHandler volleyHandler) {

        mContext = context;
        mVolleyHandler = volleyHandler;


    }

    public void PostCall(String func, final Map<String, String> params, final RequestTypes requestTypes) {

        String url = mContext.getResources().getString(R.string.server_id) + func + mContext.getResources().getString(R.string.server_key);

        AppInit.stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppInit.stringRequest = null;
                assert response != null;
                try {

                    JSONObject js = new JSONObject(response);
                    if (js.getString("result").equalsIgnoreCase("fail")) {
                        try {
                            if (js.getString("reason").equalsIgnoreCase("Authentication Failure")) {

                                Intent logOut = new Intent(mContext, SplashActivity.class);
                                logOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                mContext.startActivity(logOut);
                                ((Activity) mContext).finish();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    mVolleyHandler.onSuccess(requestTypes, new JSONObject(response));

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if(AppInit.ConntectedToInternet){
                    AppInit.stringRequest = null;
                }
                mVolleyHandler.onError(requestTypes, error);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(mContext,
                            mContext.getString(R.string.error_network_timeout),
                            Toast.LENGTH_LONG).show();
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        AppInit.stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppInit.requestQueue.add(AppInit.stringRequest);
    }
}