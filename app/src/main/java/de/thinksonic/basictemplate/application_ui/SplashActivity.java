package de.thinksonic.basictemplate.application_ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.VolleyError;
import org.json.JSONObject;

import de.thinksonic.basictemplate.app_initializer.AppInit;
import de.thinksonic.basictemplate.app_initializer.BaseActivity;
import de.thinksonic.basictemplate.R;
import de.thinksonic.basictemplate.Tools.RequestTypes;

/*
    Created By : Shesha Vasukhi Prasad
    Date : 15-Jul-2019
    Time : 22:07
    For Project : BasicTemplate
*/

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void initViews() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo ni = manager.getActiveNetworkInfo();

        if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            if(!initialized) {
                initialized = true;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        AppInit.SplashCreated = true;
                        Intent main = new Intent(SplashActivity.this, TrainerActivity.class);
                        startActivity(main);
                        finish();
                    }
                }, 3000);
            }
        }
    }

    @Override
    public void onSuccess(RequestTypes requestTypes, JSONObject jsonObject) {

    }

    @Override
    public void onError(RequestTypes requestTypes, VolleyError volleyError) {

    }

}
